package com.team.case6.core.mapper.impl;


import com.team.case6.core.mapper.EntityMapper;
import com.team.case6.core.mapper.IUserMapper;
import com.team.case6.core.model.dto.UserInfoDTO;
import com.team.case6.core.model.entity.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper implements IUserMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserInfo toEntity(UserInfoDTO dto) {
        return modelMapper.map(dto, UserInfo.class);
    }

    @Override
    public UserInfoDTO toDto(UserInfo entity) {
        UserInfoDTO infoDTO = modelMapper.map(entity, UserInfoDTO.class);
        infoDTO.setUsername(entity.getUser().getUsername());
        return infoDTO;
    }

    @Override
    public List<UserInfo> toEntity(List<UserInfoDTO> dtoList) {
        List<UserInfo> userInfos = new ArrayList<>();
        for (UserInfoDTO element : dtoList) {
            userInfos.add(toEntity(element));
        }
        return userInfos;
    }

    @Override
    public List<UserInfoDTO> toDto(List<UserInfo> entityList) {
        List<UserInfoDTO> userInfos = new ArrayList<>();
        for (UserInfo element : entityList) {
            userInfos.add(toDto(element));
        }
        return userInfos;
    }

    @Override
    public void updateFromDTO(UserInfoDTO dto, UserInfo entity) {
       modelMapper.map(dto, entity);
    }


}
