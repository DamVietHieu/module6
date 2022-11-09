package com.team.case6.like.controller;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.model.dto.ResponseMessage;
import com.team.case6.like.mapper.ILikeMapper;
import com.team.case6.like.model.Like;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.blog.service.blog.IBlogService;
import com.team.case6.blog.service.blogStautus.IBlogStatusService;
import com.team.case6.like.model.LikeDTO;
import com.team.case6.like.service.ILikeService;
import com.team.case6.core.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogStatusService blogStatusService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private ILikeMapper likeMapper;

    @GetMapping("/{idBlog}")
    public ResponseEntity<List<LikeDTO>> getListLikeByBlog(@PathVariable Long idBlog) {
        List<Like> list = likeService.findAllByBlogId(idBlog);
        List<LikeDTO> likeDTOS = likeMapper.toDto(list);
        return new ResponseEntity<>(likeDTOS, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveLike(@RequestBody LikeDTO likeDTO) {
        Optional<UserInfo> userInfo = userInfoService.findById(likeDTO.getIdUser());
        Optional<Blog> blog = blogService.findById(likeDTO.getIdBlog());
        if (!userInfo.isPresent() || !blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (likeService.findAllByBlogAndAndUserInfo(blog.get(), userInfo.get()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        Like like = new Like();
        like.setBlog(blog.get());
        like.setUserInfo(userInfo.get());
        blog.get().setCountLike(blog.get().getCountLike() + 1L);
        blogService.save(blog.get());
        likeService.save(like);
        return new ResponseEntity<>(new ResponseMessage(true, "like Success"), HttpStatus.OK);
    }


    @PostMapping("/unlike")
    public ResponseEntity<?> deleteLike(@RequestBody LikeDTO likeDTO) {
        Optional<UserInfo> userInfo = userInfoService.findById(likeDTO.getIdUser());
        Optional<Blog> blog = blogService.findById(likeDTO.getIdBlog());
        if (!userInfo.isPresent() || !blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Like> like = likeService.findAllByBlogAndAndUserInfo(blog.get(), userInfo.get());
        if (!like.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        likeService.removeById(like.get().getId());
        blog.get().setCountLike(blog.get().getCountLike() - 1L);
        blogService.save(blog.get());
        return new ResponseEntity<>(new ResponseMessage(false, "Unlike Success"), HttpStatus.OK);
    }
}
