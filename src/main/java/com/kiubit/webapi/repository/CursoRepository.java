package com.kiubit.webapi.repository;

import com.kiubit.webapi.auth.models.UsuarioModel;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface CursoRepository {
    public List<Curso> getAllCursos();

    public Map<String, Object> registrarCurso(RegistrarCursoRequest request);

    public Map<String, Object> selectCurso();
}
