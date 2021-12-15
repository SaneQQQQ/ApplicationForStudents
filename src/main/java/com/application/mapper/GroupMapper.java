package com.application.mapper;

import com.application.dto.GroupDTO;
import com.application.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDTO groupToGroupDTO(Group group);

    Group groupDTOToGroup(GroupDTO dto);

}