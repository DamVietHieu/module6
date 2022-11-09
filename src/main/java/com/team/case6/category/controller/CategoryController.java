package com.team.case6.category.controller;

import com.team.case6.blog.service.blog.IBlogService;
import com.team.case6.category.mapper.ICategoryMapper;
import com.team.case6.category.model.CategoryDTO;
import com.team.case6.core.model.dto.PictureForm;
import com.team.case6.category.model.Category;
import com.team.case6.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categorySV;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryMapper categoryMapper;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getListCategory() {
        for (Category element : categorySV.findAll()) {
            int numberBLog = blogService.findBlogPublicByCategory(element).size();
            element.setCountBlog(Long.parseLong(String.valueOf(numberBLog)));
            categorySV.save(element);
        }
        return new ResponseEntity<>(categoryMapper.toDto(categorySV.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long idCategory) {
        return new ResponseEntity<>(categoryMapper.toDto(categorySV.findById(idCategory).get()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categorySV.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @ModelAttribute PictureForm pictureForm,
                                                   @PathVariable Long idCategory) {
        categorySV.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long idCategory) {
        categorySV.removeById(idCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
