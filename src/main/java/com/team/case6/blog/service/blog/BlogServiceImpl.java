package com.team.case6.blog.service.blog;


import com.team.case6.category.model.Category;
import com.team.case6.blog.model.DTO.BlogMostLike;
import com.team.case6.blog.model.DTO.BlogsOfUser;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.model.entity.Status;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.blog.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    IBlogRepo iBlogRepo;
    @Override
    public List<Blog> findAll() {
        return iBlogRepo.findAll();
    }

    @Override
    public Blog save(Blog blog) {
        return iBlogRepo.save(blog);
    }

    @Override
    public void removeById(Long id) {
    iBlogRepo.deleteById(id);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return iBlogRepo.findById(id);
    }

    @Override
    public List<Blog> findAllByCategory_Name(String categoryName) {
        return iBlogRepo.findAllByCategory_Name(categoryName);
    }

    @Override
    public List<Blog> findAllByCategory(Category category) {
        return iBlogRepo.findAllByCategory(category);
    }

    @Override
    public List<BlogsOfUser> findBlogsOfUser() {
        return iBlogRepo.findBlogsOfUser();
    }

    @Override
    public List<BlogMostLike> findBlogsMostLike() {
        return iBlogRepo.findBlogsMostLike();
    }

    @Override
    public List<Blog> findAllByUserInfo(UserInfo userInfo) {
        return iBlogRepo.findAllByUserInfo(userInfo);
    }

    @Override
    public List<Blog> findBlogPublic() {
        return iBlogRepo.findAll().stream().filter(b->b.getBlogStatus().getStatus() == Status.PUBLIC&&b.getBlogStatus().isConfirm()).collect(Collectors.toList());
    }
    @Override
    public List<Blog> findBlogPrivate() {
        return iBlogRepo.findAll().stream().filter(b->b.getBlogStatus().getStatus() == Status.PRIVATE).collect(Collectors.toList());
    }

    @Override
    public List<Blog> findBlogPublicByCategory(Category category) {
        return iBlogRepo.findAll().stream().filter(b->b.getCategory()==category && b.getBlogStatus().getStatus().equals(Status.PUBLIC)&&b.getBlogStatus().isConfirm()).collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserInfo(UserInfo userInfo) {
        return iBlogRepo.existsByUserInfo(userInfo);
    }
}
