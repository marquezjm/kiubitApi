package com.kiubit.webapi.repository;

import com.kiubit.webapi.auth.models.UsuarioModel;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface CursoRepository {
    public List<Curso> getAllCursos();

    public Map<String, Object> registrarCurso(RegistrarCursoRequest request);

    public Map<String, Object> selectCurso(SelectCursoRequest request);

    public Map<String, Object> selectModulo(SelectModuloRequest request);
    public Map<String, Object> selectTema(SelectTemaRequest request);

    public Map<String, Object> registrarModulo(RegistrarModuloRequest request);

    public Map<String, Object> seleccionarUltimoVisto(String usuario);
    public Map<String, Object> seleccionarSuscripcionesUsuario(String usuario);
    public Map<String, Object> seleccionarCursoById(String usuario);
}
