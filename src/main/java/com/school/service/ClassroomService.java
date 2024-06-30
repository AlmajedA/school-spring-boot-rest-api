package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.DTO.ClassroomDTO;
import com.school.Mapper.ClassroomMapper;
import com.school.model.Classroom;
import com.school.repository.ClassroomRepository;

import jakarta.transaction.Transactional;

@Service
public class ClassroomService {

    // (Create classroom - get classroom by id - get all classrooms - update classroom)
    ClassroomRepository classroomRepository;
    ClassroomMapper classroomMapper;

    // Autowiring
    public ClassroomService(ClassroomRepository classroomRepository, ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
    }

    public void createClassroomService(ClassroomDTO classroomDTO){
        Classroom classroom = classroomMapper.classroomDTOToClassroom(classroomDTO);
        classroomRepository.save(classroom);
    }

    public ClassroomDTO getClassroomByIdService(Long id){
        Classroom classroom = classroomRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Classroom is not exist"));

        ClassroomDTO classroomDTO = classroomMapper.classroomToClassroomDTO(classroom);
        return classroomDTO;
    }

    public List<ClassroomDTO> getAllClassroomsService(){
        List<Classroom> classrooms = classroomRepository.findAll();

        List<ClassroomDTO> classroomDTOs = classroomMapper.classroomToClassroomDTO(classrooms);

        return classroomDTOs;
    }

    @Transactional
    public void updateClassroom(Long id, int room, int building){
        Classroom classroom = classroomRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Classroom is not exist"));

        classroom.setRoom(room);
        classroom.setBuilding(building);
    }

}
