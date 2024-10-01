package com.Unisc.TC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Banca {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TrabalhoDeConclusao trabalho;

    // Getters e Setters
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
}
