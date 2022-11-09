package com.team.case6.core.mapper;

import com.team.case6.core.model.dto.UserInfoDTO;
import com.team.case6.core.model.entity.UserInfo;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper extends EntityMapper<UserInfoDTO, UserInfo>{
}
