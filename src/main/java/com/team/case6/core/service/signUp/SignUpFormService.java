package com.team.case6.core.service.signUp;

import com.team.case6.core.config.AppConstants;
import com.team.case6.core.mapper.impl.UserMapper;
import com.team.case6.core.model.VerificationToken;
import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.User;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.model.entity.UserStatus;
import com.team.case6.core.repository.ISignUpFormRepo;
import com.team.case6.core.repository.ITokenRepo;
import com.team.case6.core.service.mail.MailServiceImpl;
import com.team.case6.core.service.user.UserService;
import com.team.case6.core.service.userInfo.UserInfoService;
import com.team.case6.core.service.userStatus.IUserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SignUpFormService implements ISignUpFormService {
    @Autowired
    private ISignUpFormRepo signUpFormRepo;
    @Autowired
    private ITokenRepo tokenRepo;
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private IUserStatusService userStatusService;

    @Override
    public List<SignUpForm> findAll() {
        return signUpFormRepo.findAll();
    }

    @Override
    public SignUpForm save(SignUpForm signUpForm) {
        return signUpFormRepo.save(signUpForm);
    }

    @Override
    public void removeById(Long id) {
        signUpFormRepo.deleteById(id);

    }

    @Override
    public Optional<SignUpForm> findById(Long id) {
        return signUpFormRepo.findById(id);
    }

    @Override
    public SignUpForm findUserByEmail(String email) {
        return signUpFormRepo.findByEmail(email);
    }

    @Override
    @Transactional(value = "transactionManager")
    public SignUpForm registerNewUser(SignUpForm user) {
        String avatar = "profile.png";
        Date now = Calendar.getInstance().getTime();
        user.setAvatar(avatar);
        user.setCreatedDate(now);
        user = signUpFormRepo.save(user);
        signUpFormRepo.flush();
        return user;
    }

    @Override
    public void createVerificationTokenForUser(SignUpForm signUpForm, String token) {
        final VerificationToken myToken = new VerificationToken(token, signUpForm);
        tokenRepo.save(myToken);
    }

    @Override
    public boolean resendVerificationToken(String token) {
        VerificationToken vToken = tokenRepo.findByToken(token);
        if (vToken != null) {
            vToken.updateToken(UUID.randomUUID().toString());
            vToken = tokenRepo.save(vToken);
            mailService.sendVerificationToken(vToken.getToken(), vToken.getSignUpForm());
            return true;
        }
        return false;
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepo.findByToken(token);
        if (verificationToken == null) {
            return AppConstants.TOKEN_INVALID;
        }

        final SignUpForm signUpForm = verificationToken.getSignUpForm();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return AppConstants.TOKEN_EXPIRED;
        }

        User user = new User(signUpForm.getUsername(), signUpForm.getPassword());
        UserStatus userStatus = new UserStatus();
        UserInfo userInfo = new UserInfo(
                signUpForm.getName(),
                signUpForm.getEmail(),
                signUpForm.getAvatar(),
                signUpForm.getAbout(),
                signUpForm.getBirthDay(),
                signUpForm.getCreatedDate().toString(),
                userStatus,
                user
        );
        userService.save(user);
        userStatusService.save(userStatus);
        userInfoService.save(userInfo);
        tokenRepo.delete(verificationToken);
        signUpFormRepo.deleteById(signUpForm.getId());
        return AppConstants.TOKEN_VALID;
    }
}
