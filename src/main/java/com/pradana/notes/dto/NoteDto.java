package com.pradana.notes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NoteDto {
    @NotNull(message = "Judul harus di isi")
    @NotBlank(message = "Judul tidak boleh kosong")
    @Size(min = 4, message = "Min judul terdiri dari 4 karakter")
    private String title;

    @NotNull(message = "Deskripsi harus di isi")
    @NotBlank(message = "Deskripsi tidak boleh kosong")
    @Size(min = 4, message = "Min deskripsi terdiri dari 4 karakter")
    private String description;

    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "Hanya bisa di isi true atau false")
    private String isCompleted;
}
