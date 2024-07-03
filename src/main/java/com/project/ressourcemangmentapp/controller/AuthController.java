package com.project.ressourcemangmentapp.controller;

import com.project.ressourcemangmentapp.auth.AuthenticationRequest;
import com.project.ressourcemangmentapp.auth.AuthenticationResponse;
import com.project.ressourcemangmentapp.auth.RegisterRequest;
import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.service.AuthService;
import com.project.ressourcemangmentapp.service.JwtService;
import com.project.ressourcemangmentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        User user = userService.findByEmail(request.getEmail());
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validatToken(@RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");
        String email = jwtService.extractEmail(tokenWithoutBearer);
        var userDetails = userDetailsService.loadUserByUsername(email);
        if(jwtService.isTokenValid(tokenWithoutBearer, userDetails)) {
            User user = userService.findByEmail(email);
            System.out.println("user: " + user.getId());
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User has no roles"))
                    .getAuthority();
            return ResponseEntity.ok(new AuthenticationResponse(jwtService.generateToken(userDetails), role, user.getId()));
        } else {
            return ResponseEntity.status(401).build();
        }

    }
}
