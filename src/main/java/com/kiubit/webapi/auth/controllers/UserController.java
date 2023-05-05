package com.kiubit.webapi.auth.controllers;

import com.kiubit.webapi.auth.models.UsuarioModel;
import com.kiubit.webapi.auth.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
//@RequestMapping("usuario")
public class UserController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping("/getById")
    public ResponseEntity getById(@RequestBody UsuarioModel usuarioBody){
        Optional<UsuarioModel> usuario = repository.findByEmail(usuarioBody.getEmail());
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }


}
