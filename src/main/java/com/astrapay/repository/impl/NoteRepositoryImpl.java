package com.astrapay.repository.impl;

import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class NoteRepositoryImpl implements NoteRepository {
    private final Map<UUID, Note> storage = new ConcurrentHashMap<>();

    @Override
    public List<Note> findAll(int page, int size, String sort) {
        Stream<Note> noteStream = storage.values().stream();
        Comparator<Note> comparator;

        switch (sort.toLowerCase()) {
            case "oldest":
            case "asc":
                comparator = Comparator.comparing(Note::getCreatedAt);
                break;
            case "newest":
            case "desc":
            default:
                comparator = Comparator.comparing(Note::getCreatedAt).reversed();
                break;
        }

        return noteStream
                .sorted(comparator)
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
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

    @Override
    public long count() {
        return storage.size();
    }
}
