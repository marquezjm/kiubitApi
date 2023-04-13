package com.kiubit.webapi.services;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursosService {

    @Autowired
    private CursoRepository repository;

    public CommonResponse<List<Curso>> getCursos(){
        List<Curso> lista = new ArrayList<>();
        try{
            lista=repository.getAllCursos();
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<List<Curso>>(lista,false,"Error");
        }
        return new CommonResponse<List<Curso>>(lista,true,"Exito");
    }
}
