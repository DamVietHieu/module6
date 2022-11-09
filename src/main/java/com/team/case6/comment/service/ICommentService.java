package com.team.case6.comment.service;


import com.team.case6.comment.model.Comment;
import com.team.case6.core.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {

    List<Comment> findAllByBlog_Id(Long blogId);
    Long getCountCommentByBlogId(Long blogId);

    void deleteBlog(Long blogId);
}
