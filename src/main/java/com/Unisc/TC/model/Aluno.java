package com.Unisc.TC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_id")
    private TrabalhoDeConclusao tc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orientador_id")
    private Professor orientador;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TrabalhoDeConclusao getTc() {
        return tc;
    }

    public void setTc(TrabalhoDeConclusao tc) {
        this.tc = tc;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public void setOrientador(Professor orientador) {
        this.orientador = orientador;
    }
}
