package com.project.ressourcemangmentapp.model.dto;

import com.project.ressourcemangmentapp.model.Break;
import com.project.ressourcemangmentapp.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private float serialNumber;
    private String job;
    private Role role;
}
