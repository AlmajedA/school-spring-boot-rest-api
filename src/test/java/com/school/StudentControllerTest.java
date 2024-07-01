package com.school;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.DTO.StudentDTO;
import com.school.controller.StudentController;
import com.school.exception.NotFoundException;
import com.school.model.Student;
import com.school.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1L);
        
        doNothing().when(studentService).createStudentService(any(Student.class));

        mockMvc.perform(post("/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated());
                
        verify(studentService).createStudentService(any(Student.class));
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1L ,"Ammar", "SWE", null);
        
        when(studentService.getStudentByIdService(1L)).thenReturn(studentDTO);

        mockMvc.perform(get("/v1/students/1"))
                .andExpect(status().isOk());
                
        verify(studentService).getStudentByIdService(1L);
    }

    @Test
    public void testGetStudentByIdNotFound() throws Exception {
        when(studentService.getStudentByIdService(1L)).thenThrow(new NotFoundException("student with id: 1 does not exist"));

        mockMvc.perform(get("/v1/students/1"))
                .andExpect(status().isNotFound());
                
        verify(studentService).getStudentByIdService(1L);
    }

    @Test
    public void testGetAllStudents() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO(1L ,"Ammar", "SWE", null);

        StudentDTO studentDTO2 = new StudentDTO(2L ,"Ahmed", "SWE", null);

        List<StudentDTO> studentList = Arrays.asList(studentDTO1, studentDTO2);

        when(studentService.getAllStudentsService()).thenReturn(studentList);

        mockMvc.perform(get("/v1/students"))
                .andExpect(status().isOk());
                
        verify(studentService).getAllStudentsService();
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student studentDetails = new Student();
        studentDetails.setStudentId(1L);
        studentDetails.setName("Updated Name");
        studentDetails.setMajor("Updated Major");

        doNothing().when(studentService).updateStudentService(eq(1L), any(Student.class));

        mockMvc.perform(put("/v1/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDetails)))
                .andExpect(status().isCreated());
                
        verify(studentService).updateStudentService(eq(1L), any(Student.class));
    }

    @Test
    public void testUpdateStudentNotFound() throws Exception {
        Student studentDetails = new Student();
        studentDetails.setStudentId(1L);
        studentDetails.setName("Updated Name");
        studentDetails.setMajor("Updated Major");

        doThrow(new NotFoundException("student with id: 1 does not exist")).when(studentService).updateStudentService(eq(1L), any(Student.class));

        mockMvc.perform(put("/v1/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDetails)))
                .andExpect(status().isNotFound());
                
        verify(studentService).updateStudentService(eq(1L), any(Student.class));
    }

    @Test
    public void testAssignStudentToClassroom() throws Exception {
        doNothing().when(studentService).assignStudentToClassroomService(1L, 1L);

        mockMvc.perform(put("/v1/students/1/assign-classroom/1"))
                .andExpect(status().isCreated());
                
        verify(studentService).assignStudentToClassroomService(1L, 1L);
    }

    @Test
    public void testAssignStudentToClassroomStudentNotFound() throws Exception {
        doThrow(new NotFoundException("student with id: 1 does not exist")).when(studentService).assignStudentToClassroomService(1L, 1L);

        mockMvc.perform(put("/v1/students/1/assign-classroom/1"))
                .andExpect(status().isNotFound());
                
        verify(studentService).assignStudentToClassroomService(1L, 1L);
    }

    @Test
    public void testAssignStudentToClassroomClassroomNotFound() throws Exception {
        doThrow(new NotFoundException("classroom with id: 1 does not exist")).when(studentService).assignStudentToClassroomService(1L, 1L);

        mockMvc.perform(put("/v1/students/1/assign-classroom/1"))
                .andExpect(status().isNotFound());
                
        verify(studentService).assignStudentToClassroomService(1L, 1L);
    }
}
