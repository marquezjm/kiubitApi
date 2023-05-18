package com.kiubit.webapi.repository.impl;

import com.kiubit.webapi.beans.CallProcedures;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.Modulo;
import com.kiubit.webapi.models.Tema;
import com.kiubit.webapi.models.cursos.*;
import com.kiubit.webapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CursoRepositoryImpl implements CursoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CallProcedures callProcedures;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<Curso> getAllCursos() {
        String sql="SELECT * FROM cursos";
        List<Curso> lista = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Curso.class));
        return lista;
    }

    @Override
    public Map<String, Object> registrarCurso(RegistrarCursoRequest request) {
        List<SqlParameter> typeParams = new ArrayList<>();
        typeParams.add(new SqlParameter("p_nombre", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_imagen", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_descripcion", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_usuario", Types.VARCHAR));
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_nombre", request.getNombre())
                .addValue("p_imagen", request.getImagen())
                .addValue("p_descripcion", request.getDescripcion())
                .addValue("p_usuario", request.getUsuario());
        return callProcedures.myStoredProcedureCall("CREAR_CURSO",typeParams,RegistrarCursoResponse.class,SqlResponse.class).execute(params);
    }

    @Override
    public Map<String, Object> selectCurso(SelectCursoRequest request) {
        List<SqlParameter> typeParams = new ArrayList<>();
        typeParams.add(new SqlParameter("p_nombre", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_descripcion", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_pagina", Types.INTEGER));
        typeParams.add(new SqlParameter("p_size", Types.INTEGER));
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_nombre", request.getNombre())
                .addValue("p_descripcion", request.getDescripcion())
                .addValue("p_pagina", request.getPagina())
                .addValue("p_size", request.getSize());
        return callProcedures.myStoredProcedureCall("SELECT_CURSO",typeParams,Curso.class,SqlResponse.class).execute(params);
    }

    @Override
    public Map<String, Object> registrarModulo(RegistrarModuloRequest request) {
        List<SqlParameter> typeParams = new ArrayList<>();
        typeParams.add(new SqlParameter("p_id_curso", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_nombre", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_descripcion", Types.VARCHAR));
        typeParams.add(new SqlParameter("p_usuario", Types.VARCHAR));
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_id_curso", request.getIdCurso())
                .addValue("p_nombre", request.getNombre())
                .addValue("p_descripcion", request.getDescripcion())
                .addValue("p_usuario", request.getUsuario());
        return callProcedures.myStoredProcedureCall("CREAR_MODULO",typeParams,RegistrarCursoResponse.class,SqlResponse.class).execute(params);
    }

    @Override
    public Map<String, Object> selectModulo(SelectModuloRequest request) {
        List<SqlParameter> typeParams = new ArrayList<>();
        typeParams.add(new SqlParameter("p_id_curso", Types.VARCHAR));
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_id_curso", request.getIdCurso());
        return callProcedures.myStoredProcedureCall("SELECT_MODULO",typeParams, Modulo.class,SqlResponse.class).execute(params);
    }

    @Override
    public Map<String, Object> selectTema(SelectTemaRequest request) {
        List<SqlParameter> typeParams = new ArrayList<>();
        typeParams.add(new SqlParameter("p_id_modulo", Types.VARCHAR));
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_id_modulo", request.getIdModulo());
        return callProcedures.myStoredProcedureCall("SELECT_TEMAS",typeParams, Tema.class,SqlResponse.class).execute(params);
    }

    /*public SimpleJdbcCall myStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CREAR_CURSO")
                .declareParameters(
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_imagen", Types.VARCHAR),
                        new SqlParameter("p_descripcion", Types.VARCHAR),
                        new SqlParameter("p_usuario", Types.VARCHAR)
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(RegistrarCursoResponse.class))
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(SqlResponse.class));
        return jdbcCall;
    }*/


}
