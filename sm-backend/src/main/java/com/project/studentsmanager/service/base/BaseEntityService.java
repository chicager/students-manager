package com.project.studentsmanager.service.base;

import com.project.studentsmanager.repository.BaseEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class BaseEntityService<T> {
    @Autowired
    private BaseEntityRepository<T> repository;

    protected Optional<T> findEntityById(Long id) {
        return repository.findById(id);
    }

    protected void deleteEntityById(Long id) {
        repository.deleteById(id);
    }
}
