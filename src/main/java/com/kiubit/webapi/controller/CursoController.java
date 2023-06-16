package com.kiubit.webapi.controller;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.*;
import com.kiubit.webapi.services.CursosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/cursos")
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
    @PostMapping(path = "registrarCurso")
    public CommonResponse<RegistrarCursoResponse> registrarCurso(@RequestBody(required = true) RegistrarCursoRequest request){
        return service.registrarCurso(request);
    }

    @Operation(summary = "select",description = "Select cursos")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "select")
    public CommonResponse<List<Curso>> selectCurso(@RequestBody SelectCursoRequest request){
        return service.selectCurso(request);
    }

    @Operation(summary = "registrar",description = "Registrar un nuevo Modulo")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "registrarModulo")
    public CommonResponse<RegistrarCursoResponse> registrarModulo(@RequestBody(required = true) RegistrarModuloRequest request){
        return service.registrarModulo(request);
    }

    @Operation(summary = "seleccionarUltimoVisto",description = "Seleccionar ultimo video Visto")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "seleccionarUltimoVisto")
    public CommonResponse<SeleccionarUltimoVistoResponse> seleccionarUltimoVisto(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return service.seleccionarUltimoVisto(userDetails.getUsername());
    }

    @Operation(summary = "seleccionarSuscripcionesUsuario",description = "Seleccionar suscripciones de usuarios")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "seleccionarSuscripcionesUsuario")
    public CommonResponse<List<SeleccionarSuscripcionesUsuarioResponse>> seleccionarSuscripcionesUsuario(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return service.seleccionarSuscripcionesUsuario(userDetails.getUsername());
    }

    @Operation(summary = "seleccionarCursoById",description = "Seleccionar curso por id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "seleccionarCursoById/{id_curso}")
    public CommonResponse<SeleccionarCursoByIdResponse> seleccionarCursoById(@PathVariable(value="id_curso") String id_curso){
        return service.seleccionarCursoById(id_curso);
    }

    @Operation(summary = "selectContenidoCurso",description = "Seleccionar contenido de curso")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "selectContenidoCurso/{id_curso}")
    public CommonResponse<List<Curso>> selectCurso(@PathVariable(value="id_curso") String id_curso){
        return service.selectContenidoCurso(id_curso);
    }
}
