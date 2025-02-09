package com.cjrp.base_001_app_todoist.security.service;



import com.cjrp.base_001_app_todoist.dto.AuthResponse;
import com.cjrp.base_001_app_todoist.dto.LoginRequest;
import com.cjrp.base_001_app_todoist.dto.RegisterRequest;
import com.cjrp.base_001_app_todoist.entity.Role;
import com.cjrp.base_001_app_todoist.entity.Usuario;
import com.cjrp.base_001_app_todoist.repository.UsuarioRepository;
import com.cjrp.base_001_app_todoist.security.SecurityUser;
import com.cjrp.base_001_app_todoist.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

// (9)
// Role. 3 actualiza el registro para que adhiera el role


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();

        // Convierte Usuario a SecurityUser (que implementa UserDetails)
        SecurityUser securityUser = new SecurityUser(user);

        String token = jwtService.getToken(securityUser);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {

        Usuario user = Usuario.builder() // Crea un nuevo usuario con contraseña encriptada
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fecha(new Date())
                .role(Role.USER)
                .build();

        usuarioRepository.save(user); //guarda al usuario

        SecurityUser securityUser = new SecurityUser(user);  // Convierte Usuario a SecurityUser (que implementa UserDetails)

        String token = jwtService.getToken(securityUser); // Genera el token para el usuario recién creado

        return AuthResponse.builder()
                .token(token)
                .build();
    }




}
