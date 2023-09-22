package com.amigoscode.spring_amigoscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.spring_amigoscode.dtos.CursoDTO;
import com.amigoscode.spring_amigoscode.models.CursoModel;
import com.amigoscode.spring_amigoscode.services.CursoService;

@RestController
@RequestMapping("/api/v1/curso")
public class CursoController {
    
    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listaCursos(){
        List<CursoDTO> lista = service.getCursos();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<CursoDTO> acharCurso(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(
            service.getCursoEspecifico(id).get()
        );
    }

    @PostMapping
    public ResponseEntity<CursoModel> adicionarCurso(@RequestBody CursoModel curso){
        return ResponseEntity.status(201).body(
            service.addCurso(curso)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarCurso(@PathVariable("id") long id){
        return ResponseEntity.status(202).body(
            service.deletarCurso(id)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualizarCurso(@PathVariable("id") long id, CursoModel curso){
        return ResponseEntity.status(200).body(
            service.atualizarCurso(id, curso)
        );
    }

}
