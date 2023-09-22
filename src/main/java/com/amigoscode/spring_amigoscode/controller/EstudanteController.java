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

import com.amigoscode.spring_amigoscode.dtos.EstudanteBasicoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.amigoscode.spring_amigoscode.services.EstudanteService;

@RestController
@RequestMapping("/api/v1/estudante")
public class EstudanteController {

    @Autowired
    private EstudanteService service;

    @GetMapping
    public ResponseEntity<List<EstudanteBasicoDTO>> listaEstudantes(){
        List<EstudanteBasicoDTO> lista = service.getEstudantes();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstudanteBasicoDTO> acharEstudante(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(
            service.getEstudanteEspecifico(id).get()
        );
    }

    @PostMapping
    public ResponseEntity<String> adicionarEstudante(@RequestBody EstudanteModel estudante){
        service.addEstudante(estudante);

        return ResponseEntity.status(201).body("Estudante adicionado com Sucesso");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarEstudante(@PathVariable("id") long id){
        return ResponseEntity.status(202).body( service.deletarEstudante(id) );
    }

    @PutMapping("{id}")
    public ResponseEntity<EstudanteModel> atualizarEstudante(@PathVariable("id") long id, @RequestBody EstudanteModel estudante){
        return ResponseEntity.status(200).body(
            service.atualizarEstudante(id, estudante)
        );
    }
}
