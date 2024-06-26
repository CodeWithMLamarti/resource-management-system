package com.project.ressourcemangmentapp.model;

import com.project.ressourcemangmentapp.utils.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "breaks")
public class Break {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}


