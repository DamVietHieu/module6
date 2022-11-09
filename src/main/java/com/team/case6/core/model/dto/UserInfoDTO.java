package com.team.case6.core.model.dto;

import com.team.case6.core.model.entity.Status;
import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String avatar;
    private String about;
    private String birthday;
    private String registerDate;
}
