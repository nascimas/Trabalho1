package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Unisc.TC.model.Usuario;
import com.Unisc.TC.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para adicionar um novo usuário ao banco
    @PostMapping("/addUsuario")
    public String addUsuario(@RequestParam("nome") String nome, @RequestParam("senha") String senha, @RequestParam("tipo") String tipo) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);

        usuarioRepository.save(usuario); // Salva o usuário no banco

        return "redirect:/login"; // Redireciona de volta para a página de login
    }
}
