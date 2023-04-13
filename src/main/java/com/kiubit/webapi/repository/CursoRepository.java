package com.kiubit.webapi.repository;

import com.kiubit.webapi.auth.models.UsuarioModel;
import com.kiubit.webapi.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository {
    public List<Curso> getAllCursos();
}
