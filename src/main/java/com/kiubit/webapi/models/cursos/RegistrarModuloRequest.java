package com.kiubit.webapi.models.cursos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarModuloRequest {

    private String idCurso;
    private String nombre;
    private String descripcion;
    private String usuario;
}
