package com.team.case6.blog.service.blogStautus;


import com.team.case6.blog.model.entity.BlogStatus;
import com.team.case6.core.service.IGeneralService;

import java.util.Optional;

public interface IBlogStatusService extends IGeneralService<BlogStatus> {

    Optional<BlogStatus> findByBlogId(Long blogId);
}
