package com.amigoscode.spring_amigoscode.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cursos")
public class CursoModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome_curso;

    @Column(nullable = false, length = 300)
    private String descricao;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal carga_horaria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EstudanteModel> lista_estudantes;
    
    CursoModel(){}

    public CursoModel(Long id, 
                        String nome_curso, 
                        String descricao, 
                        BigDecimal carga_horaria){
        this.id = id;
        this.nome_curso = nome_curso;
        this.descricao = descricao;
        this.carga_horaria = carga_horaria;
    }

    public CursoModel(String nome_curso, 
                    String descricao, 
                    BigDecimal carga_horaria){
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

    public Set<EstudanteModel> getLista_estudantes() {
        return lista_estudantes;
    }

    public void setLista_estudantes(Set<EstudanteModel> lista_estudantes) {
        this.lista_estudantes = lista_estudantes;
    }

    
}
