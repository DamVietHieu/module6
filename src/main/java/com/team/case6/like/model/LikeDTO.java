package com.team.case6.like.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private Long id;
    private Long idBlog;
    private Long idUser;
    private String username;
}
