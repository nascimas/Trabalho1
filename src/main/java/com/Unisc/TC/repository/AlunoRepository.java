package com.Unisc.TC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Unisc.TC.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
