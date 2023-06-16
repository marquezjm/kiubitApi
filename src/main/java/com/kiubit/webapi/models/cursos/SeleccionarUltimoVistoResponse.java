package com.kiubit.webapi.models.cursos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SeleccionarUltimoVistoResponse {

    private String id;
    private String nombre_curso;
    private String nombre_modulo;
    private String tipo_modulo;
    private int duracion_modulo;
    private String nombre_tema;
    private String tipo_tema;
    private int duracion_tema;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getNombre_modulo() {
        return nombre_modulo;
    }

    public void setNombre_modulo(String nombre_modulo) {
        this.nombre_modulo = nombre_modulo;
    }

    public String getTipo_modulo() {
        return tipo_modulo;
    }

    public void setTipo_modulo(String tipo_modulo) {
        this.tipo_modulo = tipo_modulo;
    }

    public int getDuracion_modulo() {
        return duracion_modulo;
    }

    public void setDuracion_modulo(int duracion_modulo) {
        this.duracion_modulo = duracion_modulo;
    }

    public String getNombre_tema() {
        return nombre_tema;
    }

    public void setNombre_tema(String nombre_tema) {
        this.nombre_tema = nombre_tema;
    }

    public String getTipo_tema() {
        return tipo_tema;
    }

    public void setTipo_tema(String tipo_tema) {
        this.tipo_tema = tipo_tema;
    }

    public int getDuracion_tema() {
        return duracion_tema;
    }

    public void setDuracion_tema(int duracion_tema) {
        this.duracion_tema = duracion_tema;
    }
}
