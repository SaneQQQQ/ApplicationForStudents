package com.application.mapper;

import com.application.dto.GroupDTO;
import com.application.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDTO toDTO(Group group);

    Group toEntity(GroupDTO dto);

}