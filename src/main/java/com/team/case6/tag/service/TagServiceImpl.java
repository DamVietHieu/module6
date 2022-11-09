package com.team.case6.tag.service;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.category.model.Category;
import com.team.case6.tag.model.Tag;
import com.team.case6.tag.repository.ITagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    ITagRepo tagRepo;

    @Override
    public List<Tag> findAll() {
        return tagRepo.findAll();
    }

    @Override
    public Set<Tag> findAllByBlog(Blog blog) {
        return tagRepo.findAllByBlogContaining(blog);
    }

    @Override
    public Set<Tag> findAllByCategory(Category category) {
        return tagRepo.findAllByCategoryContaining(category);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagRepo.findByName(name);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepo.save(tag);
    }

    @Override
    public void removeById(Long id) {
            tagRepo.deleteById(id);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepo.findById(id);
    }
}
