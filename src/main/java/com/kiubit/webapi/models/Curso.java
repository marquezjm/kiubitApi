package com.kiubit.webapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    private String id;
    private String nombre;
    private String imagen;
    private String descripcion;

}
