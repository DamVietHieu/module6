package com.team.case6.core.controller;

import com.team.case6.core.mapper.IUserMapper;
import com.team.case6.core.model.dto.PictureForm;
import com.team.case6.core.model.dto.UserInfoDTO;
import com.team.case6.core.model.entity.User;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.model.entity.UserStatus;
import com.team.case6.core.service.user.UserService;
import com.team.case6.core.service.userInfo.IUserInfoService;
import com.team.case6.core.service.userStatus.IUserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private IUserStatusService userStatusService;
    @Value("${file-upload-user}")
    private String uploadPathUser;
    @Autowired
    private IUserMapper iUserMapper;

    @GetMapping("")
    public ResponseEntity<List<UserInfo>> getListUserInfo(){
        return new ResponseEntity<>( userInfoService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> findById(@PathVariable Long id) {
        Optional<UserInfo> userInfo=userInfoService.findById(id);
        if (!userInfo.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iUserMapper.toDto(userInfoService.findById(id).get()), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserInfo> findByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userInfoService.findByUserId(userId), HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> updateProfile(@PathVariable Long userId, @RequestBody UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoService.findByUserId(userId);
       iUserMapper.updateFromDTO(userInfoDTO,userInfo);
        userInfoService.save(userInfo);
        return new ResponseEntity<>(iUserMapper.toDto(userInfo), HttpStatus.OK);
    }

    @PatchMapping("/avatar/{userId}")
    public ResponseEntity<UserInfoDTO> editAvatar(@PathVariable Long userId, @RequestBody String img) {
        UserInfo userInfo = userInfoService.findByUserId(userId);
        userInfo.setAvatar(img);
        userInfoService.save(userInfo);
        return new ResponseEntity<>(iUserMapper.toDto(userInfo), HttpStatus.OK);
    }
    @GetMapping("/ban/{id}")
    public ResponseEntity<UserStatus> banUser(@PathVariable Long id){
        UserStatus userStatus=userStatusService.findById(id).get();
        userStatus.setVerify(false);
        userStatusService.save(userStatus);
        return new ResponseEntity<>(userStatus, HttpStatus.OK);
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<UserStatus> activeUser(@PathVariable Long id){
        UserStatus userStatus=userStatusService.findById(id).get();
        userStatus.setVerify(true);
        userStatusService.save(userStatus);
        return new ResponseEntity<>(userStatus, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserInfo> deleteUserInfo(@PathVariable Long userId) {
        UserInfo userInfo1 = userInfoService.findByUserId(userId);
        userInfoService.removeById(userInfo1.getId());
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

