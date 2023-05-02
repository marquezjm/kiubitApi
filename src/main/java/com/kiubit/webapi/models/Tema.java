package com.kiubit.webapi.models;

import jakarta.persistence.Column;

public class Tema {

    @Column(name = "id")
    private String id;

    @Column(name = "id_modulo")
    private String idModulo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion_h")
    private int horas;

    @Column(name = "duracion_m")
    private int minutos;

    @Column(name = "duracion_s")
    private int segundos;

}
