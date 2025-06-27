package com.astrapay.service.impl;

import com.astrapay.entity.Note;
import com.astrapay.exception.NoteNotFoundException;
import com.astrapay.repository.NoteRepository;
import com.astrapay.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note addNote(String content) {
        LocalDateTime now = LocalDateTime.now();
        UUID id = UUID.randomUUID();
        Note note = new Note(id, content, now);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(UUID id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("Note not found!");
        }
        noteRepository.delete(id);
    }

    @Override
    public Optional<Note> getNoteById(UUID id) {
        return noteRepository.findById(id);
    }
}
