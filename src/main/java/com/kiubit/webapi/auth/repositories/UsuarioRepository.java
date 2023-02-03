package com.kiubit.webapi.auth.repositories;

import com.kiubit.webapi.auth.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,String> {

    Optional<UsuarioModel> findByEmail(String email);

}
