package com.team.case6;

import com.team.case6.core.mapper.IUserMapper;
import com.team.case6.core.model.dto.UserInfoDTO;
import com.team.case6.core.model.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Case6ApplicationTests {

    @Test
    void contextLoads() {
    }

    private ModelMapper modelMapper = new ModelMapper();



}
