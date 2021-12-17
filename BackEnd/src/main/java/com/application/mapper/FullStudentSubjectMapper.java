package com.application.mapper;

import com.application.dto.FullStudentSubjectDTO;
import com.application.model.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FullStudentSubjectMapper {

    FullStudentSubjectMapper INSTANCE = Mappers.getMapper(FullStudentSubjectMapper.class);

    FullStudentSubjectDTO toDTO(StudentSubject studentSubject);

    StudentSubject toEntity(FullStudentSubjectDTO dto);

}
