package com.team.case6.core.repository;

import com.team.case6.core.model.dto.SignUpForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISignUpFormRepo extends JpaRepository<SignUpForm ,Long> {

SignUpForm findByEmail(String email);

}
