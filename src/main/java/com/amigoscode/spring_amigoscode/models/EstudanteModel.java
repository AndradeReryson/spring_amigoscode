package com.amigoscode.spring_amigoscode.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.repository.CursoRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@NamedNativeQuery(
    name = "findAllEstudantesWithCurso",
    query = "SELECT e.id , e.nome_estudante, e.email, c.nome_curso, c.carga_horaria "+
            "FROM Estudantes e "+ 
            "INNER JOIN Cursos c "+ 
            "ON e.curso_id = c.id",
    resultClass = EstudanteCursoDTO.class,
    resultSetMapping = "estudanteCursoDTO_mapping"
)

@SqlResultSetMapping(
    name = "estudanteCursoDTO_mapping",
    classes = {
        @ConstructorResult(
            targetClass = EstudanteCursoDTO.class,
            columns = {
                @ColumnResult(name = "id",              type = Long.class),
                @ColumnResult(name = "nome_estudante",  type = String.class),
                @ColumnResult(name = "email",           type = String.class),
                @ColumnResult(name = "nome_curso",      type = String.class),
                @ColumnResult(name = "carga_horaria",   type = BigDecimal.class)
            }
        )
    }
)
@Entity
@Table(name = "Estudantes")
public class EstudanteModel implements Serializable{

    @Transient
    @Autowired
    CursoRepository repository;
    
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
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoModel curso;

    // Sinaliza que esse campo não vai ser uma coluna no banco. Será calculado no getIdade
    @Transient
    private Integer idade;

    EstudanteModel(){}

    public EstudanteModel(Long id,
                            String nome_estudante,
                            String email,
                            LocalDate data_nasc,
                            CursoModel curso){
        this.id = id;
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.data_nasc = data_nasc;
        this.curso = curso;
    }

    /* 
    public EstudanteModel(String nome_estudante,
                            String email,
                            LocalDate data_nasc,
                            Long id_curso){
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.data_nasc = data_nasc;
        this.curso = repository.findById(id_curso).get();
    }
    */

    public EstudanteModel(String nome_estudante, 
                            String email, 
                            LocalDate data_nasc,
                            CursoModel curso) {
        this.nome_estudante = nome_estudante;
        this.email = email;
        this.data_nasc = data_nasc;
        this.curso = curso;
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

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }

    
}
