package com.kiubit.webapi.controller;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.services.CursosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/cursos")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Curso Controller", description = "Controlador para cursos")
public class CursoController {

    @Autowired
    private CursosService service;

    @Operation(summary = "Demo Map", description = "Demo Map")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public CommonResponse<List<Curso>> getCursos(){
        return service.getCursos();
    }
}
