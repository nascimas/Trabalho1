package com.Unisc.TC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unisc.TC.model.Professor;
import com.Unisc.TC.model.TrabalhoDeConclusao;
import com.Unisc.TC.repository.ProfessorRepository;
import com.Unisc.TC.repository.TrabalhoDeConclusaoRepository;

@Controller
@RequestMapping("/trabalhos")
public class TrabalhoDeConclusaoWebController {

    @Autowired
    private TrabalhoDeConclusaoRepository trabalhoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // Exibe a lista de trabalhos e o formulário para adicionar novo trabalho
    @GetMapping
    public String showTrabalhosForm(Model model) {
        // Verifica se há professores no banco de dados, caso contrário, adiciona 3 professores genéricos
        List<Professor> professores = professorRepository.findAll();
        if (professores.isEmpty()) {
            Professor professor1 = new Professor();
            professor1.setNome("Leonel");
            professorRepository.save(professor1);

            Professor professor2 = new Professor();
            professor2.setNome("Kurt");
            professorRepository.save(professor2);

            Professor professor3 = new Professor();
            professor3.setNome("Bagattini");
            professorRepository.save(professor3);

            professores = professorRepository.findAll();  // Recarrega a lista de professores
        }

        model.addAttribute("trabalhos", trabalhoRepository.findAll());
        model.addAttribute("trabalho", new TrabalhoDeConclusao());
        model.addAttribute("professores", professores);  // Adiciona a lista de professores ao modelo
        return "home";
    }

    // Salva o novo trabalho
    @PostMapping
    public String saveTrabalho(@ModelAttribute TrabalhoDeConclusao trabalho) {
        Professor professor = professorRepository.findById(trabalho.getOrientador().getId())
                .orElseThrow(() -> new IllegalArgumentException("Professor inválido"));
        trabalho.setOrientador(professor);  // Define o professor como orientador
        trabalhoRepository.save(trabalho);  // Salva o trabalho
        return "redirect:/trabalhos";
    }

    // Carrega a página de edição de trabalho
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        TrabalhoDeConclusao trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trabalho inválido:" + id));
        model.addAttribute("trabalho", trabalho);
        model.addAttribute("professores", professorRepository.findAll());  // Inclui os professores no formulário de edição
        return "edit";
    }

    // Atualiza o trabalho
    @PostMapping("/update/{id}")
    public String updateTrabalho(@PathVariable("id") Integer id, @ModelAttribute TrabalhoDeConclusao trabalho) {
        trabalhoRepository.save(trabalho);
        return "redirect:/trabalhos";
    }

    // Exclui um trabalho
    @GetMapping("/delete/{id}")
    public String deleteTrabalho(@PathVariable("id") Integer id) {
        TrabalhoDeConclusao trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trabalho inválido:" + id));
        trabalhoRepository.delete(trabalho);
        return "redirect:/trabalhos";
    }
}
