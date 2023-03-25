package com.kiubit.webapi.auth.controllers;

import com.kiubit.webapi.auth.models.AuthenticationRequest;
import com.kiubit.webapi.auth.models.AuthenticationResponse;
import com.kiubit.webapi.auth.models.CommonResponse;
import com.kiubit.webapi.auth.models.RegisterRequest;
import com.kiubit.webapi.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CommonResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
