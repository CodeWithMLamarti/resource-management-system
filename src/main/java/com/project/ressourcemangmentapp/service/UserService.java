package com.project.ressourcemangmentapp.service;

import com.project.ressourcemangmentapp.exceptions.UserNotFoundException;
import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import com.project.ressourcemangmentapp.model.dto.UserUpdateDto;
import com.project.ressourcemangmentapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<UserDto> getAllUsers() {
        return userRepository.findAllUsersWithoutPassword();
    }
    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.getUserById(id).isPresent()) {
            userRepository.deleteUserById(id);
        }
    }

    public UserDto getUser(Long id) {
        return userRepository.getUserById(id).get();
    }
    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email).get();
    }

    @Transactional
    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        float serialNumber = user.getSerialNumber();
        // Copy non-null properties from DTO to entity
        BeanUtils.copyProperties(userUpdateDto, user, getNullPropertyNames(userUpdateDto));

        // Encode password if it is present
        if (userUpdateDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
        if(userUpdateDto.getSerialNumber() == 0) {
            user.setSerialNumber(serialNumber);
        }

        userRepository.save(user);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
