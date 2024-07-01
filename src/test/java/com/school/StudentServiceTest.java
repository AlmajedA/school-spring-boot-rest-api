package com.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.school.DTO.StudentDTO;
import com.school.Mapper.StudentMapper;
import com.school.exception.NotFoundException;
import com.school.model.Classroom;
import com.school.model.Student;
import com.school.repository.ClassroomRepository;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testCreateStudentService() {
        Student student = new Student();
        studentService.createStudentService(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGetStudentByIdService() {
        Student student = new Student("Ammar", "SWE");
    
        student.setStudentId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        StudentDTO studentDTO = new StudentDTO(1L ,"Ammar", "SWE", null);
        when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        StudentDTO result = studentService.getStudentByIdService(1L);
        assertNotNull(result);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetStudentByIdServiceNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.getStudentByIdService(1L));
    }

    @Test
    public void testGetAllStudentsService() {
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);
        StudentDTO studentDTO1 = new StudentDTO(1L ,"Ammar", "SWE", null);;
        StudentDTO studentDTO2 = new StudentDTO(2L ,"Ahmed", "SWE", null);;
        when(studentMapper.studentToStudentDTO(students)).thenReturn(Arrays.asList(studentDTO1, studentDTO2));

        List<StudentDTO> result = studentService.getAllStudentsService();
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateStudentService() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setName("Old Name");
        student.setMajor("Old Major");

        Student updatedDetails = new Student();
        updatedDetails.setName("New Name");
        updatedDetails.setMajor("New Major");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.updateStudentService(1L, updatedDetails);

        assertEquals("New Name", student.getName());
        assertEquals("New Major", student.getMajor());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateStudentServiceNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        Student updatedDetails = new Student();
        assertThrows(NotFoundException.class, () -> studentService.updateStudentService(1L, updatedDetails));
    }

    @Test
    public void testAssignStudentToClassroomService() {
        Student student = new Student();
        student.setStudentId(1L);
        Classroom classroom = new Classroom();
        classroom.setClassroomId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom));

        studentService.assignStudentToClassroomService(1L, 1L);

        assertEquals(classroom, student.getClassroom());
        verify(studentRepository, times(1)).findById(1L);
        verify(classroomRepository, times(1)).findById(1L);
    }

    @Test
    public void testAssignStudentToClassroomServiceStudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.assignStudentToClassroomService(1L, 1L));
    }

    @Test
    public void testAssignStudentToClassroomServiceClassroomNotFound() {
        Student student = new Student();
        student.setStudentId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classroomRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.assignStudentToClassroomService(1L, 1L));
    }
}
