package com.school.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.DTO.ClassroomDTO;
import com.school.model.Classroom;
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
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom){
        classroomService.createClassroomService(classroom);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/")
						.buildAndExpand(classroom.getClassroomId())
						.toUri(); 
		
		return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id){

        ClassroomDTO classroomDTO = classroomService.getClassroomByIdService(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(classroomDTO.getClassroomId())
						.toUri(); 
		
		return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<ClassroomDTO> getAllClassrooms(){
        return classroomService.getAllClassroomsService();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroomDetails){
        classroomService.updateClassroom(id, classroomDetails);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(id)
						.toUri(); 
		
		return ResponseEntity.created(location).build();
        
    }
}
