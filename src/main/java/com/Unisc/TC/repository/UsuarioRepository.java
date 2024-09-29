package com.Unisc.TC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Unisc.TC.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
}
