package com.application.mapper;

import com.application.dto.FullStudentDTO;
import com.application.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FullStudentMapper {

    FullStudentMapper INSTANCE = Mappers.getMapper(FullStudentMapper.class);

    FullStudentDTO toDTO(Student student);

    Student toEntity(FullStudentDTO dto);

}
