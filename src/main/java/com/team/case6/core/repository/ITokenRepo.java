package com.team.case6.core.repository;

import com.team.case6.core.model.VerificationToken;
import com.team.case6.core.model.dto.SignUpForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(SignUpForm signUpForm);
}
