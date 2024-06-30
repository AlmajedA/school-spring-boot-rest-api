package com.school.DTO;

import java.util.List;

public class ClassroomDTO {
    private final Long classroomId;
    private final int room;
    private final int building;
    private final List<StudentDTO> students;

    public ClassroomDTO(Long classroomId, int room, int building, List<StudentDTO> students) {
        this.classroomId = classroomId;
        this.room = room;
        this.building = building;
        this.students = students;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public int getRoom() {
        return room;
    }

    public int getBuilding() {
        return building;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }
}
