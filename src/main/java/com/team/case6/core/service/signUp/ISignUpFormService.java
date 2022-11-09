package com.team.case6.core.service.signUp;

import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.service.IGeneralService;

import java.util.Optional;

public interface ISignUpFormService extends IGeneralService<SignUpForm> {
    public SignUpForm registerNewUser(SignUpForm signUpForm) ;
    SignUpForm findUserByEmail(String email);

    void createVerificationTokenForUser(SignUpForm signUpForm, String token);

    boolean resendVerificationToken(String token);

    String validateVerificationToken(String token);

}
