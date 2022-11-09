package com.team.case6.comment.mapper;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.blog.service.blog.IBlogService;
import com.team.case6.comment.model.Comment;
import com.team.case6.comment.model.CommentDTO;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.service.userInfo.IUserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentMapperImpl implements ICommentMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        modelMapper.map(dto, comment);
        Optional<Blog> blogOptional = blogService.findById(dto.getIdBlog());
        if (!blogOptional.isPresent()) {
            return null;
        }
        comment.setBlog(blogOptional.get());
        Optional<UserInfo> optionalUserInfo = userInfoService.findById(dto.getIdUserInfo());
        if (!optionalUserInfo.isPresent()) {
            return null;
        }
        blogOptional.get().setCountComment(blogOptional.get().getCountComment()+1L);
        blogService.save(blogOptional.get());
        comment.setUserInfo(optionalUserInfo.get());
        return comment;
    }

    @Override
    public CommentDTO toDto(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        modelMapper.map(entity, commentDTO);
        commentDTO.setIdBlog(entity.getBlog().getId());
        commentDTO.setIdUserInfo(entity.getUserInfo().getId());
        commentDTO.setUsername(entity.getUserInfo().getName());
        commentDTO.setAvatar(entity.getUserInfo().getAvatar());
        return commentDTO;
    }

    @Override
    public List<Comment> toEntity(List<CommentDTO> dtoList) {
        return null;
    }

    @Override
    public List<CommentDTO> toDto(List<Comment> entityList) {
        try {
            List<CommentDTO> list = new ArrayList<>();
            for (Comment element : entityList) {
                list.add(toDto(element));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public void updateFromDTO(CommentDTO dto, Comment entity) {
            modelMapper.map(dto,entity);
    }
}
