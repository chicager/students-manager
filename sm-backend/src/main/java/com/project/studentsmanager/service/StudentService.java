package com.project.studentsmanager.service;

import com.project.studentsmanager.dto.StudentRqDto;
import com.project.studentsmanager.dto.StudentRsDto;
import com.project.studentsmanager.exception.StudentNotFoundException;
import com.project.studentsmanager.mapper.StudentMapper;
import com.project.studentsmanager.model.Student;
import com.project.studentsmanager.repository.StudentRepository;
import com.project.studentsmanager.service.base.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentService extends BaseEntityService<Student> {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentRsDto addStudent(StudentRqDto dto) {
        Student student = new Student();
        student.setStudentCode(UUID.randomUUID().toString());
        student.setName(dto.getName());
        student.setCity(dto.getCity());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setImageUrl(dto.getImageUrl());
        student.setGit(dto.getGit());
        student.setAbout(dto.getAbout());
        studentRepository.save(student);

        return studentMapper.map(student);
    }

    public List<StudentRsDto> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(s -> studentMapper.map(s))
                .collect(Collectors.toList());
    }

    public StudentRsDto updateStudent(StudentRqDto dto, Long id) {
        Student student = findById(id);
        student.setName(dto.getName());
        student.setCity(dto.getCity());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setImageUrl(dto.getImageUrl());
        student.setGit(dto.getGit());
        student.setAbout(dto.getAbout());
        studentRepository.save(student);

        return studentMapper.map(student);
    }

    public StudentRsDto findStudentById(Long id) {
        return studentMapper.map(findById(id));
    }

    Student findById(Long id) {
        return super.findEntityById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student by id %d was not found", id)));
    }

    public void deleteStudent(Long id) {
        super.deleteEntityById(id);
    }
}
