package com.team.case6.core.service.userInfo;


import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.service.IGeneralService;

public interface IUserInfoService extends IGeneralService<UserInfo> {
    UserInfo findByUserId(Long id);
    Long findUserByUserInfo(Long id);
    boolean existByEmail(String email);
}
