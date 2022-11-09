package com.team.case6.tag.mapper;

import com.team.case6.core.mapper.EntityMapper;
import com.team.case6.tag.model.Tag;
import com.team.case6.tag.model.TagDTO;

import java.util.List;
import java.util.Set;

public interface ITagMapper extends EntityMapper<TagDTO, Tag> {
     Set<TagDTO> toDto(Set<Tag> tags);
}
