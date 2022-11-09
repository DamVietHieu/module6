package com.team.case6.tag.mapper;

import com.team.case6.tag.model.Tag;
import com.team.case6.tag.model.TagDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagMapperImpl implements ITagMapper{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Tag toEntity(TagDTO dto) {
        return modelMapper.map(dto,Tag.class);
    }

    @Override
    public TagDTO toDto(Tag entity) {
        return modelMapper.map(entity,TagDTO.class);
    }

    @Override
    public List<Tag> toEntity(List<TagDTO> dtoList) {
        List<Tag> list=new ArrayList<>();
        for (TagDTO element: dtoList) {
            list.add(toEntity(element));
        }
        return list;
    }

    @Override
    public List<TagDTO> toDto(List<Tag> entityList) {
        List<TagDTO> list=new ArrayList<>();
        for (Tag element: entityList) {
            list.add(toDto(element));
        }
        return list;
    }

    @Override
    public Set<TagDTO> toDto(Set<Tag> tags) {
        Set<TagDTO> set=new HashSet<>();
        for (Tag element: tags) {
            set.add(toDto(element));
        }
        return set;
    }

    @Override
    public void updateFromDTO(TagDTO dto, Tag entity) {

    }
}
