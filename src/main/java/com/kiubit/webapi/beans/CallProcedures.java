package com.kiubit.webapi.beans;

import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoResponse;
import com.kiubit.webapi.models.cursos.SqlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;

@Configuration
public class CallProcedures {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public SimpleJdbcCall myStoredProcedureCall() {
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
    }

    @Bean
    public SimpleJdbcCall selectCursosStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SELECT_CURSO")
                .declareParameters(
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(Curso.class))
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(SqlResponse.class));
        return jdbcCall;
    }
}
