package com.project.ressourcemangmentapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "serial_number")
    private float serialNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "job")
    private String job;

    @Column(name = "break")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Break> breaks;

    @ManyToOne
    @JoinTable(name = "id_role")
    private Role role;

}
