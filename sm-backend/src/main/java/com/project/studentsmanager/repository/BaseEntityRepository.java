package com.project.studentsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntityRepository<T> extends JpaRepository<T, Long> {
}
