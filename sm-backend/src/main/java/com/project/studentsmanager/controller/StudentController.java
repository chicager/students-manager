package com.project.studentsmanager.controller;

import com.project.studentsmanager.dto.StudentRqDto;
import com.project.studentsmanager.dto.StudentRsDto;
import com.project.studentsmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    @GetMapping
    public List<StudentRsDto> getAllStudents() {
        return service.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentRsDto getStudentByID(@PathVariable("id") Long id) {
        return service.findStudentById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentRsDto addStudent(@Valid @RequestBody StudentRqDto student) {
        return service.addStudent(student);
    }

    @PutMapping("/{id}")
    public StudentRsDto updateStudent(@Valid @RequestBody StudentRqDto student, @PathVariable("id") Long id) {
        return service.updateStudent(student, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        service.deleteStudent(id);
    }
}
