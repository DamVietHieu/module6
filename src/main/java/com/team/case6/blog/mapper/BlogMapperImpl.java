package com.team.case6.blog.mapper;

import com.team.case6.blog.model.DTO.BlogDTO;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.comment.service.ICommentService;
import com.team.case6.like.service.ILikeService;
import com.team.case6.tag.mapper.ITagMapper;
import com.team.case6.tag.service.ITagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BlogMapperImpl implements IBlogMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITagMapper tagMapper;
    @Autowired
    private ITagService tagService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private ICommentService commentService;

    @Override
    public Blog toEntity(BlogDTO dto) {
        return modelMapper.map(dto, Blog.class);
    }

    @Override
    public BlogDTO toDto(Blog entity) {
        BlogDTO blogDTO = modelMapper.map(entity, BlogDTO.class);
        blogDTO.setCategoryId(entity.getCategory().getId());
        blogDTO.setCategoryName(entity.getCategory().getName());
        blogDTO.setStatus(entity.getBlogStatus().getStatus());
        blogDTO.setAvatar(entity.getUserInfo().getAvatar());
        blogDTO.setUsername(entity.getUserInfo().getName());
        blogDTO.setTag(tagMapper.toDto(tagService.findAllByBlog(entity)));
        Long numberLike = likeService.getCountLikeByBlogId(entity.getId());
        Long numberComment = commentService.getCountCommentByBlogId(entity.getId());
        blogDTO.setCountComment(numberComment);
        blogDTO.setCountLike(numberLike);
        return blogDTO;
    }

    @Override
    public List<Blog> toEntity(List<BlogDTO> dtoList) {
        List<Blog> blogs = new ArrayList<>();
        for (BlogDTO element : dtoList) {
            blogs.add(toEntity(element));
        }
        return blogs;
    }

    @Override
    public List<BlogDTO> toDto(List<Blog> entityList) {
        List<BlogDTO> blogs = new ArrayList<>();
        for (Blog element : entityList) {
            blogs.add(toDto(element));
        }
        return blogs;

    }

    @Override
    public Set<BlogDTO> toDtoSet(Set<Blog> entityList) {
        Set<BlogDTO> blogs = new HashSet<>();
        for (Blog element : entityList) {
            blogs.add(toDto(element));
        }
        return blogs;
    }

    @Override
    public void updateFromDTO(BlogDTO dto, Blog entity) {
        modelMapper.map(dto, entity);
        entity.setCountLike(0L);
        entity.setCountComment(0L);
    }
}
