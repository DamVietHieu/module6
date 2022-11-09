package com.team.case6.category.service;


import com.team.case6.core.model.dto.ShowCategory;
import com.team.case6.category.model.Category;
import com.team.case6.category.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void removeById(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }


    @Override
    public Iterable<ShowCategory> getAllCategoryByUserId(Long user_id) {
        return categoryRepo.getAllCategoryByUserId(user_id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }
}
