package com.project.ressourcemangmentapp.repository;

import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    Optional<UserDto> getUserById(Long id);
    Optional<User> findUserById(Long id);

    void deleteUserById(Long id);
    @Query("SELECT new com.project.ressourcemangmentapp.model.dto.UserDto(u.id, u.firstName, u.lastName, u.serialNumber, u.balance, u.email, u.phone, u.job, u.role) FROM User u")
    List<UserDto> findAllUsersWithoutPassword();

}
