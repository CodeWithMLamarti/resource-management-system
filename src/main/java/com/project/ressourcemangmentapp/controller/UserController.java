package com.project.ressourcemangmentapp.controller;

import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import com.project.ressourcemangmentapp.model.dto.UserUpdateDto;
import com.project.ressourcemangmentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> partialUpdateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(id, userUpdateDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
