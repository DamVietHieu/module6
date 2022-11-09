package com.team.case6.core.repository;


import com.team.case6.core.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoRepo extends JpaRepository<UserInfo, Long> {
    @Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
    UserInfo findByUserId(Long id);

    @Query(value = "select user_id from user_info where id=?1", nativeQuery = true)
    Long findUserByUserInfo(Long id);
    UserInfo findByEmail(String email);

    boolean existsByEmail(String email);
}
