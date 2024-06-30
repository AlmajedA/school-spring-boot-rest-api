package com.school.model;




import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.SequenceGenerator;

@Entity
public class Classroom {
    @Id
    @SequenceGenerator(
            name = "classroom_sequence",
            sequenceName = "classroom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "classroom_sequence"
    )
    private Long classroomId;
    private int room;
    private int building;

    public Classroom() {
    }

    public Classroom(Long classroomId, int room, int building) {
        this.classroomId = classroomId;
        this.room = room;
        this.building = building;
    }

    public Long getClassroomId() {
        return classroomId;
    }
    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public int getBuilding() {
        return building;
    }
    public void setBuilding(int building) {
        this.building = building;
    }



}
