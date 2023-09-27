package com.amigoscode.spring_amigoscode.dtos;

import java.math.BigDecimal;

public class EstudanteCursoDTO{
    private Long id;
    private String nome_estudante;
    private String email;
    private Integer idade;
    private String nome_curso;
    private BigDecimal carga_horaria;

    EstudanteCursoDTO(){}

    public EstudanteCursoDTO(Long id, 
                                String nome_estudante,
                                String email,
                                String nome_curso,
                                BigDecimal carga_horaria) {
        this.id = id;
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.nome_curso = nome_curso;
        this.carga_horaria = carga_horaria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_estudante() {
        return nome_estudante;
    }

    public void setNome_estudante(String nome_estudante) {
        this.nome_estudante = nome_estudante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public BigDecimal getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(BigDecimal carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String toString(){
        return this.id+", "+
                this.nome_estudante+", "+
                this.email+", "+
                this.idade+", "+
                this.nome_curso+", "+
                this.carga_horaria;
    }
    
}