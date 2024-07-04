package com.project.ressourcemangmentapp.model.dto;

import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.utils.DocType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocsDto {
    private Long id;

    @Enumerated(EnumType.STRING)
    private DocType docType;

    private LocalDate requestedAt;

    private UserDto user;
}
