package com.Unisc.TC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Unisc.TC.model.Cronograma;
import com.Unisc.TC.repository.CronogramaRepository;

import java.util.List;

@Controller
@RequestMapping("/cronogramas")
public class CronogramaController {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @GetMapping
    public String listCronogramas(Model model) {
        List<Cronograma> cronogramas = cronogramaRepository.findAll();
        model.addAttribute("cronogramas", cronogramas);
        return "cronogramas/index"; // Ajuste o nome da view conforme necessário
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cronograma", new Cronograma());
        return "cronogramas/create"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping
    public String saveCronograma(@ModelAttribute Cronograma cronograma) {
        cronogramaRepository.save(cronograma);
        return "redirect:/cronogramas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Cronograma cronograma = cronogramaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cronograma inválido:" + id));
        model.addAttribute("cronograma", cronograma);
        return "cronogramas/edit"; // Ajuste o nome da view conforme necessário
    }

    @PostMapping("/update/{id}")
    public String updateCronograma(@PathVariable Integer id, @ModelAttribute Cronograma cronograma) {
        cronograma.setId(id); // Assegura que o ID seja mantido
        cronogramaRepository.save(cronograma);
        return "redirect:/cronogramas";
    }

    @GetMapping("/delete/{id}")
    public String deleteCronograma(@PathVariable Integer id) {
        Cronograma cronograma = cronogramaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cronograma inválido:" + id));
        cronogramaRepository.delete(cronograma);
        return "redirect:/cronogramas";
    }
}
