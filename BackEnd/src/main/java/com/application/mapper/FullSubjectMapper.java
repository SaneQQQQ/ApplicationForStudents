package com.application.mapper;

import com.application.dto.FullSubjectDTO;
import com.application.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FullSubjectMapper {

    FullSubjectMapper INSTANCE = Mappers.getMapper(FullSubjectMapper.class);

    FullSubjectDTO toDTO(Subject subject);

    Subject toEntity(FullSubjectDTO dto);
}