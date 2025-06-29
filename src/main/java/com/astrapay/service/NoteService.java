package com.astrapay.service;

import com.astrapay.entity.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteService {
    public List<Note> getAllNotes(int page, int size, String sort);

    public Note addNote(String content);

    public void deleteNote(UUID id);

    public Optional<Note> getNoteById(UUID id);

    long getTotalNotes();
}
