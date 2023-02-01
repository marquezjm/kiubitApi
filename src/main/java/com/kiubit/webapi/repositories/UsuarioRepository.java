package com.kiubit.webapi.repositories;

import com.kiubit.webapi.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,String> {

    Optional<UsuarioModel> findByEmail(String email);

}
