package com.kiubit.webapi.models.cursos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrarCursoRequest {

    private String nombre;
    private String imagen;
    private String descripcion;
    private String usuario;
}
