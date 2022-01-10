package com.application.mapper;

import com.application.dto.SubjectDTO;
import com.application.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO toDTO(Subject subject);

    Subject toEntity(SubjectDTO dto);
}
