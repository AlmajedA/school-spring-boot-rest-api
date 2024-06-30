package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.DTO.StudentDTO;
import com.school.Mapper.StudentMapper;
import com.school.model.Student;
import com.school.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    // Services to be implemented:
    // (create student - get student by id - get all students - update student)

    private StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    // Autowiring studentRepository
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // Create Student
    public void createStudentService(StudentDTO studentDTO){
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        studentRepository.save(student);
    }

    // Get Student By ID 
    public StudentDTO getStudentByIdService(Long id){
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("student with id: " + id + " does not exist"));

        return studentMapper.studentToStudentDTO(student);
    }

    // Get All Students
    public List<StudentDTO> getAllStudentsService(){
        List<Student> students = studentRepository.findAll();
        return studentMapper.studentToStudentDTO(students);
    }

    // Update Student
    @Transactional
    public void updateStudentService(Long id, String name, String major){
        
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("student with id: " + id + " does not exist"));

        student.setName(name);
        student.setMajor(major);
    }
    
}
