package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Professor;
import com.Unisc.TC.repository.ProfessorRepository;

import java.util.List;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listProfessores(Model model) {
        List<Professor> professores = professorRepository.findAll();
        model.addAttribute("professores", professores);
        return "professores/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professores/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveProfessor(@ModelAttribute Professor professor) {
        professorRepository.save(professor);
        return "redirect:/professores";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor inválido:" + id));
        model.addAttribute("professor", professor);
        return "professores/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateProfessor(@PathVariable Integer id, @ModelAttribute Professor professor) {
        professor.setId(id); // Assegura que o ID seja mantido
        professorRepository.save(professor);
        return "redirect:/professores";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Integer id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor inválido:" + id));
        professorRepository.delete(professor);
        return "redirect:/professores";
    }
}
