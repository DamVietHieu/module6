package com.team.case6.core.repository;

import com.team.case6.core.model.VerificationToken;
import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

    VerificationToken findBySignUpForm(SignUpForm signUpForm);
}
