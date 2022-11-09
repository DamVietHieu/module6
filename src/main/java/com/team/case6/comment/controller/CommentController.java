package com.team.case6.comment.controller;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.comment.mapper.ICommentMapper;
import com.team.case6.comment.model.Comment;
import com.team.case6.comment.model.CommentDTO;
import com.team.case6.core.model.dto.ResponseMessage;
import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.blog.service.blog.IBlogService;
import com.team.case6.blog.service.blogStautus.IBlogStatusService;
import com.team.case6.comment.service.ICommentService;
import com.team.case6.like.service.ILikeService;
import com.team.case6.core.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICommentService commentService;

    @Autowired
    private ICommentMapper commentMapper;

    @PostMapping("")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        if (comment == null) {
            return new ResponseEntity<>(new ResponseMessage(false, "fail"), HttpStatus.NOT_FOUND);
        }
        comment.setCreateAt(getUpdateAt());
        commentService.save(comment);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/{idBlog}")
    public ResponseEntity<List<CommentDTO>> getCommentByBlogId(@PathVariable Long idBlog) {
        return new ResponseEntity<>(commentMapper.toDto(commentService.findAllByBlog_Id(idBlog)), HttpStatus.OK);
    }

    @GetMapping("/count/{idBlog}")
    public ResponseEntity<Integer> countListCommentByBlogId(@PathVariable Long idBlog) {
        return new ResponseEntity<>(commentService.findAllByBlog_Id(idBlog).size(), HttpStatus.OK);
    }

    @PostMapping("/{idComment}/{idBlog}/{idUserInfo}")
    public ResponseEntity<?> createComment(@PathVariable Long idBlog, @PathVariable Long idComment,
                                           @PathVariable Long idUserInfo, @RequestBody Comment comment) {
        Optional<Comment> parentComment = commentService.findById(idComment);
        Optional<UserInfo> userInfo = userInfoService.findById(idUserInfo);
        Optional<Blog> blog = blogService.findById(idBlog);
        if (!userInfo.isPresent() || !blog.isPresent() || !parentComment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setBlog(blog.get());
        comment.setUserInfo(userInfo.get());
        comment.setCreateAt(getUpdateAt());
        comment.setCommentParent(parentComment.get());
        commentService.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{idComment}")
    public ResponseEntity<?> updateComment(@PathVariable Long idComment, @RequestBody Comment comment) {
        Optional<Comment> oldComment = commentService.findById(idComment);
        if (!oldComment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldComment.get().setContent(comment.getContent());
        commentService.save(oldComment.get());
        return new ResponseEntity<>(oldComment.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable Long idComment) {
        Optional<Comment> comment = commentService.findById(idComment);
        if (!comment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.removeById(comment.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getUpdateAt() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDate.format(fmt1);
    }
}
