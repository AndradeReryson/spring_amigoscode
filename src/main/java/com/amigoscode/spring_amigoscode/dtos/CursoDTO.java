package com.amigoscode.spring_amigoscode.dtos;

import java.math.BigDecimal;

import com.amigoscode.spring_amigoscode.models.CursoModel;

public class CursoDTO {
    private Long id;
    private String nome_curso;
    private String descricao;
    private BigDecimal carga_horaria;

    CursoDTO(){}

    public CursoDTO(Long id, 
                    String nome_curso, 
                    String descricao,
                    BigDecimal carga_horaria){
        this.id = id;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
        this.carga_horaria = carga_horaria;
    }

    public CursoDTO(CursoModel curso){
        this.id = curso.getId();
        this.nome_curso = curso.getNome_curso();
        this.descricao = curso.getDescricao();
        this.carga_horaria = curso.getCarga_horaria();
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
