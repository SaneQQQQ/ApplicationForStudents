package com.application.mapper;

import com.application.dto.FullGroupDTO;
import com.application.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FullGroupMapper {

    FullGroupMapper INSTANCE = Mappers.getMapper(FullGroupMapper.class);

    FullGroupDTO toDTO(Group group);

    Group toEntity(FullGroupDTO dto);
}