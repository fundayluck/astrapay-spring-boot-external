package com.astrapay.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.astrapay.dto.PaginatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;
import com.astrapay.utility.ResponseHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "newest") String sort) {
        int pageIndex = page - 1;
        if (pageIndex < 0) pageIndex = 0;
        List<Note> notes = noteService.getAllNotes(pageIndex, size, sort);
        long totalItems = noteService.getTotalNotes();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        PaginatedResponse<Note> response = new PaginatedResponse<>(
                notes,
                pageIndex + 1   ,
                size,
                totalItems,
                totalPages,
                true,
                "success retrieve notes",
                200
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NoteRequestDto request) {
        log.info("request ={}", request);
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            return ResponseHandler.generateError("note cannot be null", HttpStatus.BAD_REQUEST);
        }
        noteService.addNote(request.getContent());
        return ResponseHandler.generateResponse("success create note!", HttpStatus.CREATED, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        UUID uuidValid = UUID.fromString(id);

        Optional<Note> note = noteService.getNoteById(uuidValid);
        log.info("note={}", note);

        if (!note.isPresent()) {
            return ResponseHandler.generateError("note not found!", HttpStatus.NOT_FOUND);
        }

        noteService.deleteNote(uuidValid);
        return ResponseHandler.generateResponse("Note deleted!", HttpStatus.OK, null);

    }
}
