package com.team.case6.comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private String createAt;
    private String username;
    private String avatar;
    private Long idUserInfo;
    private Long idBlog;
    private Long idCommentParent;
}
