package com.project.studentsmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    private String phone;
    @Column(nullable = false)
    private String email;
    private String imageUrl;
    private String git;
    @Column(length = 5000)
    private String about;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();
    @Column(nullable = false, updatable = false)
    private String studentCode;
}
