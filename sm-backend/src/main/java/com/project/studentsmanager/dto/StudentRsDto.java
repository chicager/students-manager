package com.project.studentsmanager.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentRsDto extends BaseRsDto {
    private String name;
    private String city;
    private String phone;
    private String email;
    private String imageUrl;
    private String git;
    private String about;
    private List<NoteRsDto> notes;
}
