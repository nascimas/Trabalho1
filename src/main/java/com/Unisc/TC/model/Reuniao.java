package com.Unisc.TC.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private LocalDate dataR;

    @Lob
    private byte[] arquivos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_id")
    private TrabalhoDeConclusao tc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataR() {
        return dataR;
    }

    public void setDataR(LocalDate dataR) {
        this.dataR = dataR;
    }

    public byte[] getArquivos() {
        return arquivos;
    }

    public void setArquivos(byte[] arquivos) {
        this.arquivos = arquivos;
    }

    public TrabalhoDeConclusao getTc() {
        return tc;
    }

    public void setTc(TrabalhoDeConclusao tc) {
        this.tc = tc;
    }
}
