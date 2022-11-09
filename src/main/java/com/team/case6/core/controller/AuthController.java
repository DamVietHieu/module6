package com.team.case6.core.controller;

import com.team.case6.core.config.AppConstants;
import com.team.case6.core.model.dto.ChangePassword;
import com.team.case6.core.model.dto.JwtResponse;
import com.team.case6.core.model.dto.ResponseMessage;
import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.Status;
import com.team.case6.core.model.entity.User;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.model.entity.UserStatus;
import com.team.case6.core.repository.ISignUpFormRepo;
import com.team.case6.core.service.JwtService;
import com.team.case6.core.service.mail.MailServiceImpl;
import com.team.case6.core.service.signUp.SignUpFormService;
import com.team.case6.core.service.user.IUserService;
import com.team.case6.core.service.userInfo.UserInfoService;
import com.team.case6.core.service.userStatus.IUserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserStatusService userStatusService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignUpFormService signUpFormService;

    @Autowired
    private ISignUpFormRepo signUpFormRepo;

    @Autowired
    private MailServiceImpl mailService;


    private final String defaultAvatar="https://firebasestorage.googleapis.com/v0/b/uploadfile-4defc.appspot.com/o/profile.png?alt=media&token=ba82632b-b290-43ca-8a70-a9b02de051d7";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{


        //Kiểm tra username và pass có đúng hay không
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //Lưu user đang đăng nhập vào trong context của security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(user.getUsername());
        UserInfo userInfo = userInfoService.findByUserId(currentUser.getId());
        UserStatus userStatus = userInfo.getUserStatus();
        if (!userStatus.isVerify()) {
            return new ResponseEntity<>(new ResponseMessage(false, "You are banned by Admin"), HttpStatus.LOCKED);
        }
        if(userStatus.getStatus().equals(Status.ONLINE)){
            return new ResponseEntity<>(new ResponseMessage(false, "This account are Login already"), HttpStatus.CONFLICT);
        }
        userStatus.setStatus(Status.ONLINE);
        userStatusService.save(userStatus);
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(false, "Your password is not right"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm user) {
        ResponseMessage message = new ResponseMessage();
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            message.setMessage("Confirm-password does not match password");
            message.setResult(false);
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        if (userService.isUsernameExist(user.getUsername())) {
            message.setMessage("Registration account is duplicated");
            message.setResult(false);
            return new ResponseEntity<>(message, HttpStatus.FOUND);
        }
//        if(userInfoService.existByEmail(user.getEmail())){
//            message.setMessage("Registration email is duplicated");
//            message.setResult(false);
//            return new ResponseEntity<>(message, HttpStatus.FOUND);
//        }

        SignUpForm signUpForm=signUpFormService.registerNewUser(user);
        signUpForm.setAvatar(defaultAvatar);
        final String token = UUID.randomUUID().toString();

        signUpFormService.createVerificationTokenForUser(signUpForm, token);
        mailService.sendVerificationToken(token, signUpForm);

        message.setMessage("User registered successfully");
        message.setResult(true);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }



    @PostMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        Optional<User> user = this.userService.findById(id);
        String newPassword;
        String oldPassword = changePassword.getOldPassword();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                if (changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
                    newPassword = changePassword.getNewPassword();
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        user.get().setPassword(newPassword);
        user.get().setId(id);
        this.userService.save(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    @GetMapping("/token/verify")
    public ResponseEntity<?> confirmRegistration(@NotEmpty @RequestParam String token) {
        final String result = signUpFormService.validateVerificationToken(token);
        return ResponseEntity.ok().body(new ResponseMessage(true, result));
    }

    // user activation - verification
    @PostMapping("/token/resend")
    @ResponseBody
    public ResponseEntity<?> resendRegistrationToken(@NotEmpty @RequestBody String expiredToken) {
        if (!signUpFormService.resendVerificationToken(expiredToken)) {
            return new ResponseEntity<>(new ResponseMessage(false, "Token not found!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ResponseMessage(true, AppConstants.SUCCESS));
    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<UserStatus> logout(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.findByUserId(id);
        UserStatus userStatus = userInfo.getUserStatus();
        userStatus.setLastLogin(getUpdateAt());
        userStatus.setStatus(Status.OFFLINE);
        userStatusService.save(userStatus);
        return new ResponseEntity<>(userStatus, HttpStatus.OK);
    }


    private String getUpdateAt() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDate.format(fmt1);
    }
}
