package com.project.ressourcemangmentapp.repository;

import com.project.ressourcemangmentapp.model.Break;
import com.project.ressourcemangmentapp.model.dto.BreakDto;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BreakRepository extends JpaRepository<Break, Long> {
    //List<BreakDto> findByUserId(Long userId);

}
