package com.Unisc.TC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Nota {
    private double valor;
    private String criterio;
    private String etapa;

    @ManyToOne
    private Professor professor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TrabalhoDeConclusao trabalho;

    // Getters e Setters para id e trabalho
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TrabalhoDeConclusao getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(TrabalhoDeConclusao trabalho) {
        this.trabalho = trabalho;
    }

    // Novo Getter e Setter para Professor
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // Novo Getter e Setter para Criterio
    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    // Novo Getter e Setter para Etapa
    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
}
