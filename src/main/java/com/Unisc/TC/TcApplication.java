package com.Unisc.TC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.Optional;

import com.Unisc.TC.model.*;
import com.Unisc.TC.repository.*;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Unisc.TC.repository")
@EntityScan(basePackages = "com.Unisc.TC.model")
public class TcApplication {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrabalhoDeConclusaoRepository trabalhoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private BancaRepository bancaRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TcApplication.class, args);
    }

}
