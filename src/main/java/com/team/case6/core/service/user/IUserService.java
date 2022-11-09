package com.team.case6.core.service.user;

import com.team.case6.core.model.entity.User;
import com.team.case6.core.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUserName(String username);
    boolean isUsernameExist(String username);
}
