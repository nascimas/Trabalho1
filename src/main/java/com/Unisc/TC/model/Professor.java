package com.Unisc.TC.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private boolean isCod;

    @OneToMany(mappedBy = "orientador") // Relacionamento bidirecional
    private List<TrabalhoDeConclusao> trabalhos; // Um professor pode ter vários trabalhos

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCod() {
        return isCod;
    }

    public void setCod(boolean isCod) {
        this.isCod = isCod;
    }

    public List<TrabalhoDeConclusao> getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(List<TrabalhoDeConclusao> trabalhos) {
        this.trabalhos = trabalhos;
    }
}
