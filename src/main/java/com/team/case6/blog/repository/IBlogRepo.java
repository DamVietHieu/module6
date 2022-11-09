package com.team.case6.blog.repository;


import com.team.case6.blog.model.DTO.BlogMostLike;
import com.team.case6.blog.model.DTO.BlogsOfUser;
import com.team.case6.category.model.Category;
import com.team.case6.core.model.dto.LikeCount;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepo extends JpaRepository<Blog, Long> {
    List<Blog> findAllByCategory_Name(String categoryName);
    List<Blog> findAllByCategory(Category category);

    List<Blog> findAllByUserInfo(UserInfo userInfo);

    @Query(value = "SELECT blogs.id as blogId ,count(likies.blog_id) as countL FROM case4.blogs inner join case4.likies on likies.blog_id = blogs.id " +
            "group by likies.blog_id  limit 10;", nativeQuery = true)
    List<LikeCount> findCount();


    // lấy ra một danh sách tên 10 account có số lượng blog nhiều nhất
    @Query(value = " select t.user_id as userId,t.user_name as userName,t.count_blog as countBlog from  case4.top_ten_blog_user t", nativeQuery = true)
    List<BlogsOfUser> findBlogsOfUser();

    // lấy ra một danh sách gồm 10 bài viết có số lượng like nhiều nhất
    @Query(value = "SELECT t.post_id as blogId,t.user_name as username,t.post_title as Title,t.category_title as category,t.post_create_at as createAt ,t.count_like as countLike FROM  case4.top_ten_like_of_blog t", nativeQuery = true)
    List<BlogMostLike> findBlogsMostLike();

    boolean existsByUserInfo(UserInfo userInfo);



}
