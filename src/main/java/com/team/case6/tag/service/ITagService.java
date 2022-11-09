package com.team.case6.tag.service;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.category.model.Category;
import com.team.case6.core.service.IGeneralService;
import com.team.case6.tag.model.Tag;

import java.util.Optional;
import java.util.Set;

public interface ITagService extends IGeneralService<Tag> {
    Set<Tag> findAllByBlog(Blog blog);
    Set<Tag> findAllByCategory(Category category);

    Optional<Tag> findByName(String name);
}
