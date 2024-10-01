package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Banca;
import com.Unisc.TC.repository.BancaRepository;

import java.util.List;

@Controller
@RequestMapping("/bancas")
public class BancaController {

    @Autowired
    private BancaRepository bancaRepository;

    @GetMapping
    public String listBancas(Model model) {
        List<Banca> bancas = bancaRepository.findAll();
        model.addAttribute("bancas", bancas);
        return "bancas/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("banca", new Banca());
        return "bancas/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveBanca(@ModelAttribute Banca banca) {
        bancaRepository.save(banca);
        return "redirect:/bancas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Banca banca = bancaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banca inválida:" + id));
        model.addAttribute("banca", banca);
        return "bancas/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateBanca(@PathVariable Integer id, @ModelAttribute Banca banca) {
        banca.setId(id); // Assegura que o ID seja mantido
        bancaRepository.save(banca);
        return "redirect:/bancas";
    }

    @GetMapping("/delete/{id}")
    public String deleteBanca(@PathVariable Integer id) {
        Banca banca = bancaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banca inválida:" + id));
        bancaRepository.delete(banca);
        return "redirect:/bancas";
    }
}
