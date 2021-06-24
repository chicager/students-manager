package com.project.studentsmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    @Column(nullable = false)
    private String text;
    @Column(nullable = false, updatable = false)
    private String noteCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", updatable = false)
    private Student student;
}
