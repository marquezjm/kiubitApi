package com.kiubit.webapi.auth.service;

import com.kiubit.webapi.auth.models.*;
import com.kiubit.webapi.auth.repositories.UsuarioRepository;
import com.kiubit.webapi.models.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UsuarioModel.builder()
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public CommonResponse<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch(BadCredentialsException e){
            return new CommonResponse<AuthenticationResponse>(new AuthenticationResponse(""),false,"Authentication Failed");
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        CommonResponse<AuthenticationResponse> response = new CommonResponse<AuthenticationResponse>(new AuthenticationResponse(jwtToken),true,"Authentication succeeded");
        return response;
    }
}
