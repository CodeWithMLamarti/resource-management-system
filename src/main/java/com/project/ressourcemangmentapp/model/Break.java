package com.project.ressourcemangmentapp.model;

import com.project.ressourcemangmentapp.utils.BreakType;
import com.project.ressourcemangmentapp.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(name = "break_type")
    @Enumerated(EnumType.STRING)
    private BreakType breakType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "hr_approved")
    private boolean hrApproved = false;

    @Column(name = "manager_approved")
    private boolean managerApproved = false;

    public Break(LocalDate startDate, LocalDate endDate, String reason, Status status, BreakType breakType, User user, boolean hrApproved, boolean managerApproved) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.breakType = breakType;
        this.user = user;
        this.hrApproved = hrApproved;
        this.managerApproved = managerApproved;
    }

    public int getBreakDuration() {
        return (int)ChronoUnit.DAYS.between(this.startDate, this.endDate);
    }

}


