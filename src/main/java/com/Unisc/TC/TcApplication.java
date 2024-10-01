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

    @PostConstruct
    public void init() {
        // Inserindo dados de exemplo para Usuario
        Usuario usuario = new Usuario();
        usuario.setNome("Usuário Exemplo");
        usuario.setSenha("senha123");
        usuario.setTipo("aluno");
        usuarioRepository.save(usuario);

        // Inserindo dados de exemplo para Professor
        Professor professor = new Professor();
        professor.setNome("Professor Exemplo");
        professor.setCod(false); // Definindo se é coordenador
        professorRepository.save(professor);

        // Inserindo dados de exemplo para TrabalhoDeConclusao
        TrabalhoDeConclusao trabalho = new TrabalhoDeConclusao();
        trabalho.setTitulo("Trabalho de Exemplo");
        trabalho.setOrientador(professor); // Relacionando o professor
        trabalho.setTrabalho("Descrição do trabalho de exemplo.");
        trabalhoRepository.save(trabalho);

        // Inserindo dados de exemplo para Reuniao
        Reuniao reuniao = new Reuniao();
        reuniao.setData("2024-10-10");
        reuniao.setLocal("Sala 101");
        reuniao.setDescricao("Reunião inicial de orientação.");
        reuniao.setArquivos("documento.pdf");
        reuniao.setTc(trabalho); // Relacionando o trabalho de conclusão
        reuniaoRepository.save(reuniao);

        // Inserindo dados de exemplo para Banca
        Banca banca = new Banca();
        banca.setData("2024-11-15");
        banca.setTrabalho(trabalho); // Relacionando o trabalho de conclusão
        bancaRepository.save(banca);

        // Inserindo dados de exemplo para Cronograma
        Cronograma cronograma = new Cronograma();
        cronograma.setAtividade("Defesa de TCC");
        cronograma.setDataInicio("2024-10-01");
        cronograma.setDataFim("2024-10-31");
        cronogramaRepository.save(cronograma);

        // Inserindo dados de exemplo para Nota
        Nota nota = new Nota();
        nota.setValor(9.5);
        nota.setTrabalho(trabalho); // Relacionando o trabalho de conclusão
        nota.setProfessor(professor); // Relacionando o professor
        nota.setCriterio("Conteúdo apresentado");
        nota.setEtapa("Defesa Final");
        notaRepository.save(nota);

        // Inserindo dados de exemplo para Aluno
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Exemplo");
        aluno.setTc(trabalho); // Relacionando o trabalho de conclusão
        aluno.setOrientador(professor); // Relacionando o orientador
        alunoRepository.save(aluno);

        System.out.println("Dados iniciais inseridos com sucesso!");

        // EXEMPLO DE UPDATE
        // Atualizando o nome do usuário inserido
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setNome("Usuário Atualizado");
            usuarioRepository.save(usuarioAtualizado);
            System.out.println("Usuário atualizado com sucesso!");
        }

        // Atualizando a descrição do trabalho
        Optional<TrabalhoDeConclusao> trabalhoExistente = trabalhoRepository.findById(trabalho.getId());
        if (trabalhoExistente.isPresent()) {
            TrabalhoDeConclusao trabalhoAtualizado = trabalhoExistente.get();
            trabalhoAtualizado.setTrabalho("Nova descrição do trabalho de exemplo.");
            trabalhoRepository.save(trabalhoAtualizado);
            System.out.println("Trabalho atualizado com sucesso!");
        }
    }

}
