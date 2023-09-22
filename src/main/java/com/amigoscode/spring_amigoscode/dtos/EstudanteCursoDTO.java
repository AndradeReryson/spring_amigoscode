package com.amigoscode.spring_amigoscode.dtos;

import java.math.BigDecimal;

public class EstudanteCursoDTO{
    private Long id;
    private String nome_estudante;
    private Integer idade;
    private String nome_curso;
    private String descricao;
    private BigDecimal carga_horaria;

    EstudanteCursoDTO(){}

    public EstudanteCursoDTO(Long id, String nome_estudante, String email, Integer idade, String nome_curso,
            String descricao, BigDecimal carga_horaria) {
        this.id = id;
        this.nome_estudante = nome_estudante;
        this.idade = idade;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(BigDecimal carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    
    
}