package com.team.case6.comment.repository;


import com.team.case6.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {

    List<Comment> findAllByBlog_Id(Long blogId);
    Long countCommentByBlog_Id(Long blogId);

    void deleteCommentByBlog_Id(Long blogId);

}
