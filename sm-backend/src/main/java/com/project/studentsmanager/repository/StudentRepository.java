package com.project.studentsmanager.repository;

import com.project.studentsmanager.model.Student;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface StudentRepository extends BaseEntityRepository<Student> {
}
