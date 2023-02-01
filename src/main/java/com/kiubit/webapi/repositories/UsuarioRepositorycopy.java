package com.kiubit.webapi.repositories;

import com.kiubit.webapi.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositorycopy {

    @Autowired
    JdbcTemplate jdbc;

    public UsuarioModel getUsuarioById(String id){
        try{
            String sql= "SELECT * FROM usuarios WHERE id = ?";
            return jdbc.queryForObject(sql,new BeanPropertyRowMapper<UsuarioModel>(UsuarioModel.class),new Object[]{id});
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }
}
