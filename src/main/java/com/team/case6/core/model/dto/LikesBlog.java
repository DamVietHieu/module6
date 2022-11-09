package com.team.case6.core.model.dto;

import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.blog.model.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesBlog {
    private Long likes;
    private Blog idBlog;
    private UserInfo idUser;

}
