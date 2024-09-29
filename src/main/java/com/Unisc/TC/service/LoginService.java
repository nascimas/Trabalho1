package com.Unisc.TC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unisc.TC.model.Usuario;
import com.Unisc.TC.repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para validar o login
    public Usuario login(String nome, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(nome);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Verifica se a senha está correta
            if (usuario.getSenha().equals(senha)) {
                return usuario; // Login bem-sucedido
            }
        }

        return null; // Login falhou
    }
}
