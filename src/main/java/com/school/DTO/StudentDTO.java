package com.school.DTO;

public class StudentDTO {
    private final Long studentId;
    private final String name;
    private final String major;
    private final SimplifiedClassroomDTO classroom;

    public StudentDTO(Long studentId, String name, String major, SimplifiedClassroomDTO classroom) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.classroom = classroom;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public SimplifiedClassroomDTO getClassroom() {
        return classroom;
    }
}
