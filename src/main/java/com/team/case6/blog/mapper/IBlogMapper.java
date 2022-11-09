package com.team.case6.blog.mapper;

import com.team.case6.blog.model.DTO.BlogDTO;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.mapper.EntityMapper;

import java.util.Set;

public interface IBlogMapper extends EntityMapper<BlogDTO, Blog> {
    Set<BlogDTO> toDtoSet(Set<Blog> entityList);
}
