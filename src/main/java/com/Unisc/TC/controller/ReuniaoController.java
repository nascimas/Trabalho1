package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Reuniao;
import com.Unisc.TC.repository.ReuniaoRepository;

import java.util.List;

@Controller
@RequestMapping("/reunioes")
public class ReuniaoController {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @GetMapping
    public String listReunioes(Model model) {
        List<Reuniao> reunioes = reuniaoRepository.findAll();
        model.addAttribute("reunioes", reunioes);
        return "reunioes/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("reuniao", new Reuniao());
        return "reunioes/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveReuniao(@ModelAttribute Reuniao reuniao) {
        reuniaoRepository.save(reuniao);
        return "redirect:/reunioes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Reuniao reuniao = reuniaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reunião inválida:" + id));
        model.addAttribute("reuniao", reuniao);
        return "reunioes/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateReuniao(@PathVariable Integer id, @ModelAttribute Reuniao reuniao) {
        reuniao.setId(id); // Assegura que o ID seja mantido
        reuniaoRepository.save(reuniao);
        return "redirect:/reunioes";
    }

    @GetMapping("/delete/{id}")
    public String deleteReuniao(@PathVariable Integer id) {
        Reuniao reuniao = reuniaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reunião inválida:" + id));
        reuniaoRepository.delete(reuniao);
        return "redirect:/reunioes";
    }
}
