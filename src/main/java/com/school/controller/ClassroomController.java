package com.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.DTO.ClassroomDTO;
import com.school.service.ClassroomService;

@RestController
@RequestMapping("/v1/classrooms")
public class ClassroomController {

    // Create classroom APIs 
    // (Create classroom - get classroom by id - get all classrooms - update classroom)

    ClassroomService classroomService;

    
    // Autowiring classroomService
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public void createClassroom(@RequestBody ClassroomDTO classroomDTO){
        classroomService.createClassroomService(classroomDTO);
    }

    @GetMapping(path = "/{id}")
    public ClassroomDTO getClassroomById(@PathVariable Long id){
        return classroomService.getClassroomByIdService(id);
    }

    @GetMapping
    public List<ClassroomDTO> getAllClassrooms(){
        return classroomService.getAllClassroomsService();
    }

    @PutMapping(path = "/{id}")
    public void updateClassroom(@PathVariable Long id, @RequestParam int room, @RequestParam int building){
        classroomService.updateClassroom(id, room, building);
    }
}
