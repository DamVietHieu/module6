package com.team.case6.core.model;

import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.User;

import javax.persistence.Entity;

@Entity
public class VerificationToken extends AbstractToken {

    private static final long serialVersionUID = -6551160985498051566L;

    public VerificationToken() {
        super();
    }

    public VerificationToken(final String token) {
        super(token);
    }

    public VerificationToken(final String token, final User user) {
        super(token, user);
    }
    public VerificationToken(final String token, final SignUpForm signUpForm) {
        super(token, signUpForm);
    }
}
