package com.project.ressourcemangmentapp.model.dto;

import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.utils.BreakType;
import com.project.ressourcemangmentapp.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreakDto {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private BreakType breakType;

    private UserDto user;

    private boolean hrApproved = false;

    private boolean managerApproved = false;

    public int getBreakDuration() {
        return (int) ChronoUnit.DAYS.between(this.startDate, this.endDate);
    }
}
