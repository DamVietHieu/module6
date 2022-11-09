package com.team.case6.tag.repository;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.category.model.Category;
import com.team.case6.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ITagRepo extends JpaRepository<Tag, Long> {
    Set<Tag> findAllByBlogContaining(Blog blog);

    Set<Tag> findAllByCategoryContaining(Category category);

    Optional<Tag> findByName(String name);



}
