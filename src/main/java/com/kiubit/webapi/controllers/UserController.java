package com.kiubit.webapi.controllers;

import com.kiubit.webapi.models.UsuarioModel;
import com.kiubit.webapi.repositories.UsuarioRepositorycopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UserController {

    @Autowired
    UsuarioRepositorycopy repository;

    @PostMapping("/getById")
    public ResponseEntity getById(@RequestBody UsuarioModel usuarioBody){
        UsuarioModel usuario = repository.getUsuarioById(usuarioBody.getId());
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}
