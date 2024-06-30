package com.project.ressourcemangmentapp.service;

import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import com.project.ressourcemangmentapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDto> getAllUsers() {
        return userRepository.findAllUsersWithoutPassword();
    }

    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.getUserById(id).isPresent()) {
            userRepository.deleteUserById(id);
        }
    }
}
