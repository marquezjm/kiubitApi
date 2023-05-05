package com.kiubit.webapi.repository.impl;

import com.kiubit.webapi.beans.CallProcedures;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoRequest;
import com.kiubit.webapi.models.cursos.RegistrarCursoResponse;
import com.kiubit.webapi.models.cursos.SqlResponse;
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
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_nombre", request.getNombre())
                .addValue("p_imagen", request.getImagen())
                .addValue("p_descripcion", request.getDescripcion())
                .addValue("p_usuario", request.getUsuario());
        return callProcedures.myStoredProcedureCall().execute(params);
    }

    @Override
    public Map<String, Object> selectCurso() {
        /*SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_nombre", request.getNombre())
                .addValue("p_imagen", request.getImagen())
                .addValue("p_descripcion", request.getDescripcion())
                .addValue("p_usuario", request.getUsuario());*/
        return callProcedures.selectCursosStoredProcedureCall().execute();
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
