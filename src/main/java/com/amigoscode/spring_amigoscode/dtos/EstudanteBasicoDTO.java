package com.amigoscode.spring_amigoscode.dtos;

import com.amigoscode.spring_amigoscode.models.EstudanteModel;

public class EstudanteBasicoDTO{
    private Long id;
    private String nome_curso;
    private Integer idade;

    EstudanteBasicoDTO(){}

    public EstudanteBasicoDTO(Long id, String nome_curso, Integer idade){
        this.id = id;
        this.nome_curso = nome_curso;
        this.idade = idade;
    }

    public EstudanteBasicoDTO(EstudanteModel estudante){
        this.id = estudante.getId();
        this.nome_curso = estudante.getNome_estudante();
        this.idade = estudante.getIdade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    
}
