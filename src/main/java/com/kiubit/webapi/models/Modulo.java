package com.kiubit.webapi.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modulo {

    @Column(name = "id")
    private String id;

    @Column(name = "id_curso")
    private String idCurso;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    private List<Tema> listaTemas;
}
