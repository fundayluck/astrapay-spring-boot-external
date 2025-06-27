package com.astrapay.repository.impl;

import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class NoteRepositoryImpl implements NoteRepository {
    private final Map<UUID, Note> storage = new ConcurrentHashMap<>();

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Note> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(UUID id) {
        storage.remove(id);
    }

    @Override
    public Note save(Note note) {
        storage.put(note.getId(), note);
        return note;
    }

    @Override
    public boolean existsById(UUID id) {
        return storage.containsKey(id);
    }
}
