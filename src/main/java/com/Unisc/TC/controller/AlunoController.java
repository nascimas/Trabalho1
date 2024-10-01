package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Aluno;
import com.Unisc.TC.repository.AlunoRepository;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public String listAlunos(Model model) {
        List<Aluno> alunos = alunoRepository.findAll();
        model.addAttribute("alunos", alunos);
        return "alunos/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno inválido:" + id));
        model.addAttribute("aluno", aluno);
        return "alunos/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateAluno(@PathVariable Integer id, @ModelAttribute Aluno aluno) {
        aluno.setId(id); // Assegura que o ID seja mantido
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/delete/{id}")
    public String deleteAluno(@PathVariable Integer id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno inválido:" + id));
        alunoRepository.delete(aluno);
        return "redirect:/alunos";
    }
}
