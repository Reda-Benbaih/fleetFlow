package org.example.fleetflow.security;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.user.AuthResponse;
import org.example.fleetflow.DTO.user.LoginRequest;
import org.example.fleetflow.DTO.user.RegisterRequest;
import org.example.fleetflow.entities.User;
import org.example.fleetflow.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .userRoles(registerRequest.getUserRoles())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return  AuthResponse.builder()
                .token(token).build();

    }

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();

    }
}

