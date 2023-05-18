package com.kiubit.webapi.models.cursos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectCursoRequest {

    private String nombre;
    private String descripcion;
    private int pagina;
    private int size;
}
