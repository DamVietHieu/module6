package com.team.case6.category.service;


import com.team.case6.core.model.dto.ShowCategory;
import com.team.case6.category.model.Category;
import com.team.case6.core.service.IGeneralService;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category> {

    Iterable<ShowCategory> getAllCategoryByUserId(Long user_id);
    Optional<Category> findByName(String name);
}
