package com.team.case6.tag.model;

import com.team.case6.blog.model.entity.Blog;
import com.team.case6.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_category")
    private Set<Category> category =new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_blog")
    private Set<Blog> blog =new HashSet<>();
}
