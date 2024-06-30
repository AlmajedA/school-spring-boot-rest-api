package com.school.DTO;

public class SimplifiedClassroomDTO {
    private final Long classroomId;
    private final int room;
    private final int building;

    public SimplifiedClassroomDTO(Long classroomId, int room, int building) {
        this.classroomId = classroomId;
        this.room = room;
        this.building = building;
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
}
