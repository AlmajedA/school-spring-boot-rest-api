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

import com.school.DTO.StudentDTO;
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
    public void createStudent(@RequestBody StudentDTO studentDTO){
        studentService.createStudentService(studentDTO);
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
    public void updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam String major){
        studentService.updateStudentService(id, name, major);
    }

    
}
