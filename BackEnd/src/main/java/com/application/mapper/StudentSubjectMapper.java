package com.application.mapper;

import com.application.dto.StudentSubjectDTO;
import com.application.model.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentSubjectMapper {

    StudentSubjectMapper INSTANCE = Mappers.getMapper(StudentSubjectMapper.class);

    StudentSubjectDTO toDTO(StudentSubject studentSubject);

    StudentSubject toEntity(StudentSubjectDTO dto);
}
