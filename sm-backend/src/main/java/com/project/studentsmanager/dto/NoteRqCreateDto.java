package com.project.studentsmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRqCreateDto {
    @NotBlank
    private String text;
    @NotNull
    private Long studentId;
}
