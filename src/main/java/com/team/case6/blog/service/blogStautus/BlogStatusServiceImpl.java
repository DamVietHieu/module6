package com.team.case6.blog.service.blogStautus;


import com.team.case6.blog.model.entity.BlogStatus;
import com.team.case6.blog.repository.IBlogStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogStatusServiceImpl implements IBlogStatusService {
    @Autowired
    IBlogStatusRepo iBlogStatusRepo;
    @Override
    public List<BlogStatus> findAll() {
        return iBlogStatusRepo.findAll();
    }

    @Override
    public BlogStatus save(BlogStatus blogStatus) {
        return iBlogStatusRepo.save(blogStatus);
    }

    @Override
    public void removeById(Long id) {
        iBlogStatusRepo.deleteById(id);
    }

    @Override
    public Optional<BlogStatus> findById(Long id) {
        return iBlogStatusRepo.findById(id);
    }


    @Override
    public Optional<BlogStatus> findByBlogId(Long blogId) {
        return Optional.empty();
    }
}
