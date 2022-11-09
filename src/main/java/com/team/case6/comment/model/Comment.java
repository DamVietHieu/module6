package com.team.case6.comment.model;


import com.team.case6.blog.model.entity.Blog;
import com.team.case6.core.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createAt;
    @ManyToOne
    private UserInfo userInfo;
    @ManyToOne
    private Blog blog;
    @ManyToOne
    private Comment commentParent;
}
