package com.amigoscode.spring_amigoscode.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Estudantes")
public class EstudanteModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome_estudante;

    @Column(nullable = false, length = 255, unique = true)
    private String email;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("data_nasc")
    @Column(nullable = false)
    private LocalDate data_nasc;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CursoModel curso;

    // Sinaliza que esse campo não vai ser uma coluna no banco. Será calculado no getIdade
    @Transient
    private Integer idade;

    EstudanteModel(){}

    public EstudanteModel(Long id,
                            String nome_estudante,
                            String email,
                            LocalDate data_nasc){
        this.id = id;
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.data_nasc = data_nasc;
    }

    public EstudanteModel(String nome_estudante, 
                            String email, 
                            LocalDate data_nasc) {
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.data_nasc = data_nasc;
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

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public Integer getIdade() {
        // esse get será utilizado pelo JPA no seu comando de findAll, portanto, podemos mudar a logica aqui no model mesmo
        return Period.between(data_nasc, LocalDate.now()).getYears();
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    
}
