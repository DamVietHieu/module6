package com.team.case6.like.mapper;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.blog.service.blog.IBlogService;
import com.team.case6.core.mapper.IUserMapper;
import com.team.case6.core.model.dto.UserInfoDTO;
import com.team.case6.core.model.entity.User;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.repository.IUserInfoRepo;
import com.team.case6.core.service.user.IUserService;
import com.team.case6.core.service.userInfo.IUserInfoService;
import com.team.case6.like.model.Like;
import com.team.case6.like.model.LikeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeMapperImpl implements ILikeMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public Like toEntity(LikeDTO dto) {
        Like like = new Like();
        modelMapper.map(dto, like);
        Optional<Blog> blog = blogService.findById(dto.getIdBlog());
        Optional<UserInfo> user = userInfoService.findById(dto.getIdUser());
        if (!blog.isPresent() || !user.isPresent()) {
            return null;
        }
        like.setBlog(blog.get());
        like.setUserInfo(user.get());
        return like;
    }

    @Override
    public LikeDTO toDto(Like entity) {
        LikeDTO likeDTO = new LikeDTO();
        modelMapper.map(entity, likeDTO);
        likeDTO.setIdBlog(entity.getBlog().getId());
        likeDTO.setIdUser(entity.getUserInfo().getId());
        likeDTO.setUsername(entity.getUserInfo().getName());
        return likeDTO;
    }

    @Override
    public List<Like> toEntity(List<LikeDTO> dtoList) {
        return null;
    }

    @Override
    public List<LikeDTO> toDto(List<Like> entityList) {
        List<LikeDTO> likeDTOS = new ArrayList<>();
        for (Like element : entityList) {
            likeDTOS.add(toDto(element));
        }
        return likeDTOS;
    }

    @Override
    public void updateFromDTO(LikeDTO dto, Like entity) {

    }
}
