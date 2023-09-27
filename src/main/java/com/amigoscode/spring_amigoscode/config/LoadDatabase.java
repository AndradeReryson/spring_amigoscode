package com.amigoscode.spring_amigoscode.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amigoscode.spring_amigoscode.models.CursoModel;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.amigoscode.spring_amigoscode.repository.CursoRepository;
import com.amigoscode.spring_amigoscode.repository.EstudanteRepository;

@Configuration
public class LoadDatabase {

    @Autowired
    private EstudanteRepository repo_estudante;

    @Autowired
    private CursoRepository repo_curso;

    @Bean
    CommandLineRunner iniciarBanco(){
        return args -> {
            
            // CURSOS

            CursoModel cur1 = new CursoModel("DESENVOLVIMENTO DE SOFTWARE MULTIPLATAFORMA", 
                                                "Curso focado em programação de aplicações desktop, web e mobile.", 
                                                new BigDecimal(2490));

            CursoModel cur2 = new CursoModel("ANALISE E DESENVOLVIMENTO DE SISTEMAS", 
                                                "Curso focado em engenharia de software e governança de TI", 
                                                new BigDecimal(2490));

            repo_curso.saveAll(Arrays.asList(cur1, cur2));

            // ESTUDANTES

            
            EstudanteModel est1 = new EstudanteModel("Reryson Santos",
                                                        "Reryson@gmail.com",
                                                        LocalDate.of(2002, 9, 11),
                                                        findCursoById(1));

            EstudanteModel est2 = new EstudanteModel("Lucas Leite", 
                                                        "Lucas.Leite@hotmail.com", 
                                                        LocalDate.of(2004, 2, 23),
                                                        findCursoById(1));

            EstudanteModel est3 = new EstudanteModel("Brayan Andrade",
                                                        "Brayan1992@hotmail.com",
                                                        LocalDate.of(1992, 1, 3),
                                                        findCursoById(2));
            

            repo_estudante.saveAll(Arrays.asList(est1, est2, est3));
            
        };
    }
    
    private CursoModel findCursoById(int id){
        Long id_long = Long.valueOf(id);
        Optional<CursoModel> curso = repo_curso.findById(id_long);

        if(!curso.isEmpty()){
            return curso.get();
        } else {
            throw new IllegalStateException("Curso não existe");
        } 
    }



}
