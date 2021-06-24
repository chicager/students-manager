package com.project.studentsmanager.service;

import com.project.studentsmanager.dto.NoteRqCreateDto;
import com.project.studentsmanager.dto.NoteRqUpdateDto;
import com.project.studentsmanager.dto.NoteRsDto;
import com.project.studentsmanager.exception.NoteNotFoundException;
import com.project.studentsmanager.mapper.NoteMapper;
import com.project.studentsmanager.model.Note;
import com.project.studentsmanager.model.Student;
import com.project.studentsmanager.repository.NoteRepository;
import com.project.studentsmanager.service.base.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService extends BaseEntityService<Note> {
    private final NoteRepository repository;
    private final StudentService studentService;
    private final NoteMapper noteMapper;

    public NoteRsDto addNote(NoteRqCreateDto dto) {
        Student student = studentService.findById(dto.getStudentId());
        Note note = new Note();
        note.setNoteCode(UUID.randomUUID().toString());
        note.setText(dto.getText());
        note.setStudent(student);
        repository.save(note);

        return noteMapper.map(note);
    }

    public List<NoteRsDto> findAllNotes(Long studentId) {
        return repository.findAllNotesByStudentId(studentId)
                .stream()
                .map(n -> noteMapper.map(n))
                .collect(Collectors.toList());
    }

    public NoteRsDto updateNote(NoteRqUpdateDto dto, Long id) {
        Note note = findById(id);
        note.setText(dto.getText());
        repository.save(note);

        return noteMapper.map(note);
    }

    public NoteRsDto findNoteById(Long id) {
        return noteMapper.map(findById(id));
    }

    private Note findById(Long id) {
        return super.findEntityById(id)
                .orElseThrow(() -> new NoteNotFoundException(
                        String.format("Note by id %d was not found", id)));
    }

    public void deleteNote(Long id) {
        super.deleteEntityById(id);
    }
}
