package com.Unisc.TC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Unisc.TC.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
