package com.school.DTO;


public class StudentDTO {
    private final Long studentId;
    private final String name;
    private final String major;

    
    public StudentDTO(Long studentId, String name, String major) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;

    }
    
    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public Long getStudentId() {
        return studentId;
    }
    
}
