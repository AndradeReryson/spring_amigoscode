package com.amigoscode.spring_amigoscode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amigoscode.spring_amigoscode.models.CursoModel;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long> {
    
    Optional<CursoModel> findByNome(String nome);
}
