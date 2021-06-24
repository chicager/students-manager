package com.project.studentsmanager.repository;


import com.project.studentsmanager.model.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface NoteRepository extends BaseEntityRepository<Note> {
    List<Note> findAllNotesByStudentId(Long studentId);
}
