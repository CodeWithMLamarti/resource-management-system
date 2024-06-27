package com.project.ressourcemangmentapp.auth;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private float serialNumber;

    private String email;

    private String password;

    private String phone;

    private String job;

    private String role;
}
