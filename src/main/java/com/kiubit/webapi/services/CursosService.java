package com.kiubit.webapi.services;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.cursos.RegistrarCursoRequest;
import com.kiubit.webapi.models.cursos.RegistrarCursoResponse;
import com.kiubit.webapi.models.cursos.SqlResponse;
import com.kiubit.webapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public CommonResponse<RegistrarCursoResponse> registrarCurso(RegistrarCursoRequest request){
        RegistrarCursoResponse id=new RegistrarCursoResponse();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.registrarCurso(request);
            id= ((List<RegistrarCursoResponse>) results.get("result1")).get(0);
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<RegistrarCursoResponse>(new RegistrarCursoResponse(""),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<RegistrarCursoResponse>(id,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<RegistrarCursoResponse>(id,true,"Exito");
    }

    public CommonResponse<List<Curso>> selectCurso(){
        List<Curso> id=new ArrayList<>();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.selectCurso();
            id= (List<Curso>) results.get("result1");
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<List<Curso>>(new ArrayList<>(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<List<Curso>>(id,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<List<Curso>>(id,true,"Exito");
    }
}
