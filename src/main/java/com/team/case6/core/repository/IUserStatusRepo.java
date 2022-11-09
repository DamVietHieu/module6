package com.team.case6.core.repository;


import com.team.case6.core.model.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserStatusRepo extends JpaRepository<UserStatus,Long> {
}
