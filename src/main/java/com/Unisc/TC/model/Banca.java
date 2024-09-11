package com.Unisc.TC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Banca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_1_id")
    private Professor prof1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_2_id")
    private Professor prof2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_id")
    private TrabalhoDeConclusao tc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Professor getProf1() {
        return prof1;
    }

    public void setProf1(Professor prof1) {
        this.prof1 = prof1;
    }

    public Professor getProf2() {
        return prof2;
    }

    public void setProf2(Professor prof2) {
        this.prof2 = prof2;
    }

    public TrabalhoDeConclusao getTc() {
        return tc;
    }

    public void setTc(TrabalhoDeConclusao tc) {
        this.tc = tc;
    }
}
