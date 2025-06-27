package com.astrapay.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NoteRequestDto {
    @NotBlank(message = "content must not be blank")
    private String content;
}
