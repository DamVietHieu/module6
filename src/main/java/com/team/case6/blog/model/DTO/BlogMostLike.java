package com.team.case6.blog.model.DTO;

public interface BlogMostLike {
    Long getBlogId();
    String getUsername();
    String getCategory();
    String getTitle();
    String getCreateAt();
    int getCountLike();
}
