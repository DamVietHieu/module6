package com.team.case6.blog.service.blog;


import com.team.case6.category.model.Category;
import com.team.case6.blog.model.DTO.BlogMostLike;
import com.team.case6.blog.model.DTO.BlogsOfUser;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.core.service.IGeneralService;

import java.util.List;

public interface IBlogService extends IGeneralService<Blog> {
    List<Blog> findAllByCategory_Name(String categoryName);
    List<Blog> findAllByCategory(Category category);
    List<Blog> findAllByUserInfo(UserInfo userInfo);
    List<BlogsOfUser> findBlogsOfUser();
    List<BlogMostLike> findBlogsMostLike();
    List<Blog> findBlogPublic();
     List<Blog> findBlogPrivate();
    List<Blog> findBlogPublicByCategory(Category category);
    boolean existsByUserInfo(UserInfo userInfo);
}
