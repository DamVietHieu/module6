package com.team.case6.category.mapper;

import com.team.case6.category.model.Category;
import com.team.case6.category.model.CategoryDTO;
import com.team.case6.tag.mapper.ITagMapper;
import com.team.case6.tag.model.Tag;
import com.team.case6.tag.model.TagDTO;
import com.team.case6.tag.service.ITagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryMapperImpl implements ICategoryMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ITagMapper iTagMapper;
@Autowired
    private ITagService tagService;

    @Override
    public Category toEntity(CategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        Set<TagDTO> tagDTOSet = iTagMapper.toDto(tagService.findAllByCategory(entity));
        CategoryDTO categoryDTO = new CategoryDTO();
        modelMapper.map(entity, categoryDTO);
        categoryDTO.setTagDTO(tagDTOSet);
        return categoryDTO;
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> dtoList) {
        return null;
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> entityList) {
        List<CategoryDTO> list = new ArrayList<>();
        for (Category element : entityList) {
            list.add(toDto(element));
        }
        return list;
    }

    @Override
    public void updateFromDTO(CategoryDTO dto, Category entity) {

    }
}
