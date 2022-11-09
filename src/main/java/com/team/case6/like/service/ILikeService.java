package com.team.case6.like.service;

import com.team.case6.core.model.dto.LikeCount;
import com.team.case6.core.model.dto.LikesBlog;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.like.model.Like;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikeService extends IGeneralService<Like> {
    Optional<Like> findAllByBlogAndAndUserInfo(Blog blog, UserInfo userInfo);

    List<Like> findAllByBlogId(Long blogId);
    List<LikesBlog> findCountLikeByBlogId(Long blogId);
    void deleteLikeByBlog(Blog blog);

    List<LikeCount> findCount();
    Long getCountLikeByBlogId(Long blog_id);

}
