package com.kiubit.webapi.services;

import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.models.Curso;
import com.kiubit.webapi.models.Modulo;
import com.kiubit.webapi.models.Tema;
import com.kiubit.webapi.models.cursos.*;
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

    public CommonResponse<RegistrarCursoResponse> registrarModulo(RegistrarModuloRequest request){
        RegistrarCursoResponse id=new RegistrarCursoResponse();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.registrarModulo(request);
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

    public CommonResponse<List<Curso>> selectCurso(SelectCursoRequest request){
        List<Curso> lista=new ArrayList<>();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.selectCurso(request);
            lista= (List<Curso>) results.get("result1");
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);

            for (Curso curso:lista){
                Map<String, Object> resultsModulo = repository.selectModulo(new SelectModuloRequest(curso.getId()));
                List<Modulo> listaModulo= (List<Modulo>) resultsModulo.get("result1");
                curso.setListaModulos(listaModulo);
                for(Modulo modulo: listaModulo){
                    Map<String, Object> resultsTema = repository.selectTema(new SelectTemaRequest(modulo.getId()));
                    List<Tema> listaTema= (List<Tema>) resultsTema.get("result1");
                    modulo.setListaTemas(listaTema);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<List<Curso>>(new ArrayList<>(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<List<Curso>>(lista,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<List<Curso>>(lista,true,"Exito");
    }

    public CommonResponse<SeleccionarUltimoVistoResponse> seleccionarUltimoVisto(String usuario){
        SeleccionarUltimoVistoResponse ultimo=new SeleccionarUltimoVistoResponse();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.seleccionarUltimoVisto(usuario);
            ultimo= ((List<SeleccionarUltimoVistoResponse>) results.get("result1")).get(0);
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<SeleccionarUltimoVistoResponse>(new SeleccionarUltimoVistoResponse(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<SeleccionarUltimoVistoResponse>(ultimo,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<SeleccionarUltimoVistoResponse>(ultimo,true,"Exito");
    }

    public CommonResponse<List<SeleccionarSuscripcionesUsuarioResponse>> seleccionarSuscripcionesUsuario(String usuario){
        List<SeleccionarSuscripcionesUsuarioResponse> lista=new ArrayList<>();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.seleccionarSuscripcionesUsuario(usuario);
            lista= (List<SeleccionarSuscripcionesUsuarioResponse>) results.get("result1");
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<List<SeleccionarSuscripcionesUsuarioResponse>>(new ArrayList<>(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<List<SeleccionarSuscripcionesUsuarioResponse>>(lista,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<List<SeleccionarSuscripcionesUsuarioResponse>>(lista,true,"Exito");
    }

    public CommonResponse<SeleccionarCursoByIdResponse> seleccionarCursoById(String id_curso){
        SeleccionarCursoByIdResponse curso=new SeleccionarCursoByIdResponse();
        SqlResponse sqlResponse = new SqlResponse();
        try{
            Map<String, Object> results = repository.seleccionarCursoById(id_curso);
            curso= ((List<SeleccionarCursoByIdResponse>) results.get("result1")).get(0);
            sqlResponse= ((List<SqlResponse>) results.get("result2")).get(0);
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<SeleccionarCursoByIdResponse>(new SeleccionarCursoByIdResponse(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<SeleccionarCursoByIdResponse>(curso,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<SeleccionarCursoByIdResponse>(curso,true,"Exito");
    }

    public CommonResponse<List<Curso>> selectContenidoCurso(String id_curso){
        List<Curso> lista=new ArrayList<>();
        SqlResponse sqlResponse = new SqlResponse();
        lista.add(new Curso(id_curso));
        try{
            Map<String, Object> resultsModulo = repository.selectModulo(new SelectModuloRequest(lista.get(0).getId()));
            List<Modulo> listaModulo= (List<Modulo>) resultsModulo.get("result1");
            sqlResponse = ((List<SqlResponse>) resultsModulo.get("result2")).get(0);
            lista.get(0).setListaModulos(listaModulo);
            for(Modulo modulo: listaModulo){
                Map<String, Object> resultsTema = repository.selectTema(new SelectTemaRequest(modulo.getId()));
                List<Tema> listaTema= (List<Tema>) resultsTema.get("result1");
                sqlResponse = ((List<SqlResponse>) resultsModulo.get("result2")).get(0);
                modulo.setListaTemas(listaTema);
            }
        }catch(Exception e){
            System.out.println(e.getMessage().toString());
            return new CommonResponse<List<Curso>>(new ArrayList<>(),false,"Error");
        }
        if(!sqlResponse.isExito()){
            return new CommonResponse<List<Curso>>(lista,false,sqlResponse.getMensaje());
        }
        return new CommonResponse<List<Curso>>(lista,true,"Exito");
    }
}
