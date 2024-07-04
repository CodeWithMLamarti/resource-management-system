package com.project.ressourcemangmentapp.repository;

import com.project.ressourcemangmentapp.model.Docs;
import com.project.ressourcemangmentapp.model.dto.DocsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocsRepository extends JpaRepository<Docs, Long> {

    @Query("SELECT d FROM Docs d WHERE d.user.id = :userId")
    List<DocsDto> findDocsByUserId(@Param("userId") Long userId);
}

