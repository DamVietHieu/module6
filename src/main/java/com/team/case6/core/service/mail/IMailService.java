package com.team.case6.core.service.mail;

import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.User;

public interface IMailService {
    void sendVerificationToken(String token, User user);
    void sendVerificationToken(String token, SignUpForm signUpForm);
}
