package com.project.studentsmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRqDto {
    @Pattern(regexp="^[A-Za-zА-Яа-я '-]{2,30}$")
    private String name;
    @Pattern(regexp="^[A-Za-zА-Яа-я '-]{2,30}$")
    private String city;
    @Pattern(regexp="^\\+?\\d*$")
    private String phone;
    @Email
    private String email;
    private String imageUrl;
    private String git;
    private String about;
}
