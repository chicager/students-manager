package com.project.studentsmanager.mapper;

import com.project.studentsmanager.dto.NoteRsDto;
import com.project.studentsmanager.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteRsDto map(Note note);
}
