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

import com.school.DTO.StudentDTO;
import com.school.model.Student;
import com.school.service.StudentService;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    // Create student APIs (Controller) 
    // (create student - get student by id - get all students - update student)


    private StudentService studentService;
    

    // Autowiring studentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        studentService.createStudentService(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/")
						.buildAndExpand(student.getStudentId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentByIdService(id);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudentsService();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
        studentService.updateStudentService(id, studentDetails);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(studentDetails.getStudentId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
    }


    @PutMapping(path = "/{studentId}/assign-classroom/{classroomId}")
    public ResponseEntity<Student> assignStudentToClassroom(@PathVariable Long studentId, @PathVariable Long classroomId){
        studentService.assignStudentToClassroomService(studentId, classroomId); 

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{studentId}/assign-classroom/{classroomId}")
						.buildAndExpand(studentId, classroomId)
						.toUri();   
		
		return ResponseEntity.created(location).build();

    
    }

    
}
