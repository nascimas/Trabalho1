package com.Unisc.TC.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Unisc.TC.model.Aluno;
import com.Unisc.TC.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAllAlunos() {
        return alunoRepository.findAll();
    }
}
