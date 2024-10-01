package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Nota;
import com.Unisc.TC.repository.NotaRepository;

import java.util.List;

@Controller
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping
    public String listNotas(Model model) {
        List<Nota> notas = notaRepository.findAll();
        model.addAttribute("notas", notas);
        return "notas/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("nota", new Nota());
        return "notas/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveNota(@ModelAttribute Nota nota) {
        notaRepository.save(nota);
        return "redirect:/notas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota inválida:" + id));
        model.addAttribute("nota", nota);
        return "notas/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateNota(@PathVariable Integer id, @ModelAttribute Nota nota) {
        nota.setId(id); // Assegura que o ID seja mantido
        notaRepository.save(nota);
        return "redirect:/notas";
    }

    @GetMapping("/delete/{id}")
    public String deleteNota(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota inválida:" + id));
        notaRepository.delete(nota);
        return "redirect:/notas";
    }
}
