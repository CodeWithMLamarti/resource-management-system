package com.project.ressourcemangmentapp.service;

import com.project.ressourcemangmentapp.auth.AuthenticationRequest;
import com.project.ressourcemangmentapp.auth.RegisterRequest;
import com.project.ressourcemangmentapp.auth.AuthenticationResponse;
import com.project.ressourcemangmentapp.utils.Role;
import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSerialNumber(request.getSerialNumber());
        user.setEmail(request.getEmail());
        user.setJob(request.getJob());
        user.setBreaks(null);
        user.setRole(Role.valueOf(request.getRole()));
        userRepository.save(user);

        var jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jtwToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

/*
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
 */
        System.out.println(request.getPassword());
        var user = userRepository.getUserByEmail(request.getEmail())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}
