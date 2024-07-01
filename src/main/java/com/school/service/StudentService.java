package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.DTO.StudentDTO;
import com.school.Mapper.StudentMapper;
import com.school.exception.NotFoundException;
import com.school.model.Classroom;
import com.school.model.Student;
import com.school.repository.ClassroomRepository;
import com.school.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    // Services to be implemented:
    // (create student - get student by id - get all students - update student)

    private StudentRepository studentRepository;
    private ClassroomRepository classroomRepository;
    private final StudentMapper studentMapper;


    // Autowiring studentRepository
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classroomRepository = classroomRepository;
    }

    // Create Student
    public void createStudentService(Student student){
        studentRepository.save(student);
    }

    // Get Student By ID 
    public StudentDTO getStudentByIdService(Long id){
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("student with id: " + id + " does not exist"));

        return studentMapper.studentToStudentDTO(student);
    }

    // Get All Students
    public List<StudentDTO> getAllStudentsService(){
        List<Student> students = studentRepository.findAll();
        return studentMapper.studentToStudentDTO(students);
    }

    // Update Student
    @Transactional
    public void updateStudentService(Long id, Student studentDetails){
        
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("student with id: " + id + " does not exist"));

        student.setName(studentDetails.getName());
        student.setMajor(studentDetails.getMajor());
    }

    // Assign student to a class service
    @Transactional
    public void assignStudentToClassroomService(Long studentId, Long classroomId){
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new NotFoundException("student with id: " + studentId + " does not exist"));
        
        Classroom classroom = classroomRepository.findById(classroomId)
        .orElseThrow(() -> new NotFoundException("classroom with id: " + classroomId + " does not exist"));
        student.setClassroom(classroom);
    }
    
}
