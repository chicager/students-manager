package com.project.studentsmanager.mapper;

import com.project.studentsmanager.dto.StudentRsDto;
import com.project.studentsmanager.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentRsDto map(Student student);
}
