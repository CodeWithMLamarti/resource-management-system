package com.project.ressourcemangmentapp.model;

import com.project.ressourcemangmentapp.utils.DocType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "docs")
public class Docs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type")
    private DocType docType;

    @Column(name = "requested_at")
    private LocalDate requestedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Docs(DocType docType, LocalDate date,User user) {
        this.docType = docType;
        this.requestedAt = date;
        this.user = user;
    }
}
