package com.team.case6.like.repository;


import com.team.case6.core.model.dto.LikeCount;
import com.team.case6.core.model.dto.LikesBlog;
import com.team.case6.blog.model.entity.Blog;
import com.team.case6.like.model.Like;
import com.team.case6.core.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikeRepo extends JpaRepository<Like, Long> {

    Optional<Like> findAllByBlogAndUserInfo(Blog blog, UserInfo userInfo);

    List<Like> findAllByBlog_Id(Long blogId);

    @Query(value = "SELECT count(user_info_id)as countLikes,blog_id,user_info_id" +
            " FROM case4.likies where blog_id=?1 " +
            "group by user_info_id ;", nativeQuery = true)
    List<LikesBlog> findCountLikeByBlogId(Long blogId);

    void removeAllByBlog(Blog blog);

    @Query(value = "SELECT blogs.id as blogId ,count(likies.blog_id) as countL FROM case4.blogs" +
            " inner join case4.likies on likies.blog_id = blogs.id " +
            "group by likies.blog_id  limit 10;", nativeQuery = true)
    List<LikeCount> findCount();

    @Query(value = "SELECT count_like FROM case6.like_of_blog where blog_id=?1 ;", nativeQuery = true)
     Long getCountLikeByBlogId(Long blog_id);
}
