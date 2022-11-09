package com.team.case6.like.model;


import com.team.case6.core.model.entity.UserInfo;
import com.team.case6.blog.model.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Likies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserInfo userInfo;
    @ManyToOne
    private Blog blog;
}
