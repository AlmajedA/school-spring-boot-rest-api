package com.school.Mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.school.DTO.ClassroomDTO;
import com.school.model.Classroom;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    ClassroomDTO classroomToClassroomDTO(Classroom classroom);
    Classroom classroomDTOToClassroom(ClassroomDTO classroomDTO);
    List<ClassroomDTO> classroomToClassroomDTO(List<Classroom> classrooms);
    List<Classroom> classroomDTOToClassroom(List<ClassroomDTO> classroomDTOs);
}
