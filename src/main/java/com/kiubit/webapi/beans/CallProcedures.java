package com.kiubit.webapi.beans;

import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoResponse;
import com.kiubit.webapi.models.cursos.SqlResponse;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;
import java.util.List;

@Configuration
public class CallProcedures {
    //@Autowired
    private final JdbcTemplate jdbcTemplate;

    public CallProcedures(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public SimpleJdbcCall myStoredProcedureCall(String procedure,List<SqlParameter> typeParams, Class result1, Class result2) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(procedure)
                /*.declareParameters(
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_imagen", Types.VARCHAR),
                        new SqlParameter("p_descripcion", Types.VARCHAR),
                        new SqlParameter("p_usuario", Types.VARCHAR)
                )*/
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(result1))//RegistrarCursoResponse.class
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(result2));
        for (SqlParameter param: typeParams){
            jdbcCall.addDeclaredParameter(param);
        }
        return jdbcCall;
    }

    public SimpleJdbcCall myStoredProcedureCall(String procedure, Class result1, Class result2) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(procedure)
                /*.declareParameters(
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_imagen", Types.VARCHAR),
                        new SqlParameter("p_descripcion", Types.VARCHAR),
                        new SqlParameter("p_usuario", Types.VARCHAR)
                )*/
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(result1))//RegistrarCursoResponse.class
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(result2));

        return jdbcCall;
    }


    public SimpleJdbcCall selectModulosStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SELECT_MODULOS")
                .declareParameters(
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(Curso.class))
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(SqlResponse.class));
        return jdbcCall;
    }


    public SimpleJdbcCall selectCursosStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SELECT_CURSO")
                .declareParameters(
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(Curso.class))
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(SqlResponse.class));
        return jdbcCall;
    }

   /*@Bean
    public SimpleJdbcCall selectCursosStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SELECT_CURSO")
                .declareParameters(
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(Curso.class))
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

    @Bean
    public SimpleJdbcCall selectCursosStoredProcedureCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SELECT_CURSO")
                .declareParameters(
                )
                .returningResultSet("result1", BeanPropertyRowMapper.newInstance(Curso.class))
                .returningResultSet("result2", BeanPropertyRowMapper.newInstance(SqlResponse.class));
        return jdbcCall;
    }*/
}
