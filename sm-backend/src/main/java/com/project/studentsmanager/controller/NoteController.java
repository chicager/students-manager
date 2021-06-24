package com.project.studentsmanager.controller;

import com.project.studentsmanager.dto.NoteRqCreateDto;
import com.project.studentsmanager.dto.NoteRqUpdateDto;
import com.project.studentsmanager.dto.NoteRsDto;
import com.project.studentsmanager.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    @GetMapping("/student/{studentId}")
    public List<NoteRsDto> getAllNotes(@PathVariable("studentId") Long studentId) {
        return service.findAllNotes(studentId);
    }

    @GetMapping("/{noteId}")
    public NoteRsDto getNoteByID(@PathVariable("noteId") Long id) {
        return service.findNoteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public NoteRsDto addNote(@Valid @RequestBody NoteRqCreateDto note) {
        return service.addNote(note);
    }

    @PutMapping("/{noteId}")
    public NoteRsDto updateNote(@Valid @RequestBody NoteRqUpdateDto note, @PathVariable("noteId") Long id) {
        return service.updateNote(note, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long id) {
        service.deleteNote(id);
    }
}
