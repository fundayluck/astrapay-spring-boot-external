package com.astrapay.repository;

import com.astrapay.entity.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository {
    List<Note> findAll(int page, int size, String sort);
    Optional<Note> findById(UUID id);
    void delete(UUID id);
    Note save(Note note);
    boolean existsById(UUID id);
    long count();
}
