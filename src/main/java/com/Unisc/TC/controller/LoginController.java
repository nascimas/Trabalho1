package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Unisc.TC.model.Usuario;
import com.Unisc.TC.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Exibe a página de login
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Renderiza o arquivo login.html
    }

    // Processa o login
    @PostMapping("/login")
    public String processLogin(@RequestParam("nome") String nome, @RequestParam("senha") String senha, Model model) {
        Usuario usuario = loginService.login(nome, senha);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);

            // Redireciona com base no tipo de usuário
            if (usuario.getTipo().equals("aluno")) {
                return "redirect:/home-aluno"; // Redireciona para a página do aluno
            } else if (usuario.getTipo().equals("orientador")) {
                return "redirect:/home-orientador"; // Redireciona para a página do orientador
            } else if (usuario.getTipo().equals("coordenador")) {
                return "redirect:/home-coordenador"; // Redireciona para a página do coordenador
            }
        }

        // Se o login falhou, retorna à página de login com mensagem de erro
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }
}
