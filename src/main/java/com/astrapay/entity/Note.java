package com.astrapay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Note {
    private UUID id;
    private String content;

    private LocalDateTime createdAt;
}
