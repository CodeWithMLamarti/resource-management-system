package com.project.ressourcemangmentapp.model.dto;

import com.project.ressourcemangmentapp.utils.Role;
import lombok.Data;

@Data
public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private float serialNumber;
    private String email;
    private String password;
    private String phone;
    private String job;
    private Role role;
}
