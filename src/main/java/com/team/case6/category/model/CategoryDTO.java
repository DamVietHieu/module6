package com.team.case6.category.model;


import com.team.case6.tag.model.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String picture;
    private Set<TagDTO> tagDTO = new HashSet<>();
    private Long countBlog;
}
