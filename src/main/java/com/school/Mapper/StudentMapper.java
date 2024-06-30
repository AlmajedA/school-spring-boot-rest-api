package com.school.Mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.school.DTO.StudentDTO;
import com.school.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO studentToStudentDTO(Student student);
    Student studentDTOToStudent(StudentDTO studentDTO);
    List<StudentDTO> studentToStudentDTO(List<Student> students);
    
}
