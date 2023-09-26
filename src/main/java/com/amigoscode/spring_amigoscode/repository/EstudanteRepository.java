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

    @Query(nativeQuery = true, name = "findAllEstudantesWithCurso")
    Optional<List<EstudanteCursoDTO>> findAllEstudantesWithCurso();
}
