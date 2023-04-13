package com.kiubit.webapi.repository.impl;

import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CursoRepositoryImpl implements CursoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Curso> getAllCursos() {
        String sql="SELECT * FROM cursos";
        List<Curso> lista = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Curso.class));
        return lista;
    }
}
