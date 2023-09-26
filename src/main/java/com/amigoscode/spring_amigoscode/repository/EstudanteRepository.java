package com.amigoscode.spring_amigoscode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteModel, Long>{
    
    @Query(value = "SELECT * FROM Estudantes e WHERE e.email = ?1", nativeQuery = true)
    Optional<EstudanteModel> findByEmail(String email);

    @Query(value = "SELECT e.id, e.nome_estudante, c.nome_curso, c.descricao, c.carga_horaria "+
                    "FROM Estudantes e "+ 
                    "INNER JOIN Cursos c "+ 
                    "ON e.curso_id = c.id", nativeQuery = true)
    Optional<List<EstudanteCursoDTO>> findAllEstudantesWithCurso();
}
