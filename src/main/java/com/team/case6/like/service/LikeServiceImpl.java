package com.team.case6.like.service;


import com.team.case6.core.model.dto.LikeCount;
import com.team.case6.core.model.dto.LikesBlog;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.like.model.Like;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.like.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private ILikeRepo likeRepo;

    @Override
    public List<Like> findAll() {
        return likeRepo.findAll();
    }

    @Override
    public Like save(Like like) {
        return likeRepo.save(like);
    }

    @Override
    public void removeById(Long id) {
        likeRepo.deleteById(id);
    }

    @Override
    public Optional<Like> findById(Long id) {
        return likeRepo.findById(id);
    }

    @Override
    public Optional<Like> findAllByBlogAndAndUserInfo(Blog blog, UserInfo userInfo) {
        return likeRepo.findAllByBlogAndUserInfo(blog, userInfo);
    }

    @Override
    public List<Like> findAllByBlogId(Long blogId) {
        return likeRepo.findAllByBlog_Id(blogId);
    }

    @Override
    public List<LikesBlog> findCountLikeByBlogId(Long blogId) {
        return likeRepo.findCountLikeByBlogId(blogId);
    }

    @Override
    public void deleteLikeByBlog(Blog blog) {
        List<Like> list = likeRepo.findAllByBlog_Id(blog.getId());
        if (!list.isEmpty()) {
            for (Like like : list) {
                try {
                    likeRepo.deleteById(like.getId());
                } catch (Exception e) {
                    System.out.println(e);
                    return;
                }
            }
        }
    }

    @Override
    public List<LikeCount> findCount() {
        return likeRepo.findCount();
    }

    @Override
    public Long getCountLikeByBlogId(Long blog_id) {
        System.out.println(likeRepo.getCountLikeByBlogId(blog_id));
        return likeRepo.getCountLikeByBlogId(blog_id);
    }
}
