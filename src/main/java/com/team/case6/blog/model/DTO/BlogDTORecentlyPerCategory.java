package com.team.case6.blog.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTORecentlyPerCategory {
    private String category;
    private List<BlogDTO> blogs;
}
