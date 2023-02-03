package com.kiubit.webapi.auth.controllers;

import com.kiubit.webapi.auth.repositories.UsuarioRepositorycopy;
import com.kiubit.webapi.auth.models.UsuarioModel;
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
