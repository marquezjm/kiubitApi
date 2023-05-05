package com.kiubit.webapi.controller;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoRequest;
import com.kiubit.webapi.models.cursos.RegistrarCursoResponse;
import com.kiubit.webapi.services.CursosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "registrar",description = "Registrar un nuevo curso")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "registrar")
    public CommonResponse<RegistrarCursoResponse> registrarCurso(@RequestBody(required = true) RegistrarCursoRequest request){
        return service.registrarCurso(request);
    }

    @Operation(summary = "select",description = "Select cursos")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "select")
    public CommonResponse<List<Curso>> selectCurso(){
        return service.selectCurso();
    }
}
