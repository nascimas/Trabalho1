package com.Unisc.TC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unisc.TC.model.TrabalhoDeConclusao;
import com.Unisc.TC.repository.TrabalhoDeConclusaoRepository;

@RestController
@RequestMapping("/api/trabalhos")
public class TrabalhoDeConclusaoController {

    @Autowired
    private TrabalhoDeConclusaoRepository trabalhoRepository;

    // Get all Trabalhos
    @GetMapping
    public List<TrabalhoDeConclusao> getAllTrabalhos() {
        return trabalhoRepository.findAll();
    }

    // Get a single Trabalho by ID
    @GetMapping("/{id}")
    public ResponseEntity<TrabalhoDeConclusao> getTrabalhoById(@PathVariable Integer id) {
        return trabalhoRepository.findById(id)
                .map(trabalho -> ResponseEntity.ok().body(trabalho))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new Trabalho
    @PostMapping
    public TrabalhoDeConclusao createTrabalho(@RequestBody TrabalhoDeConclusao trabalho) {
        return trabalhoRepository.save(trabalho);
    }

    // Update an existing Trabalho
    @PutMapping("/{id}")
    public ResponseEntity<TrabalhoDeConclusao> updateTrabalho(@PathVariable Integer id, @RequestBody TrabalhoDeConclusao trabalhoDetails) {
        return trabalhoRepository.findById(id)
                .map(trabalho -> {
                    trabalho.setTitulo(trabalhoDetails.getTitulo());
                    trabalho.setOrientador(trabalhoDetails.getOrientador());
                    TrabalhoDeConclusao updatedTrabalho = trabalhoRepository.save(trabalho);
                    return ResponseEntity.ok().body(updatedTrabalho);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a Trabalho
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrabalho(@PathVariable Integer id) {
        return trabalhoRepository.findById(id)
                .map(trabalho -> {
                    trabalhoRepository.delete(trabalho);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
