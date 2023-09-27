package com.amigoscode.spring_amigoscode.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigoscode.spring_amigoscode.dtos.EstudanteBasicoDTO;
import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.amigoscode.spring_amigoscode.repository.EstudanteRepository;

@Service
public class EstudanteService {
    
    @Autowired
    private EstudanteRepository repository;

    public List<EstudanteBasicoDTO> getEstudantes(){
        List<EstudanteModel> lista_models = repository.findAll();
        List<EstudanteBasicoDTO> lista_dtos = new ArrayList<>();

        for(EstudanteModel estudante : lista_models){
            EstudanteBasicoDTO dto = new EstudanteBasicoDTO(estudante);
            lista_dtos.add(dto);
        }

        return lista_dtos;
    }

    public Optional<EstudanteBasicoDTO> getEstudanteEspecifico(long id){
        Optional<EstudanteModel> estudante = repository.findById(id);

        EstudanteBasicoDTO dto = new EstudanteBasicoDTO(estudante.get());
        Optional<EstudanteBasicoDTO> opt_dto = Optional.of(dto);
        
        return opt_dto;
    }

    public List<EstudanteCursoDTO> getEstudantesWithCursos(){
        List<EstudanteCursoDTO> lista_dtos = repository.findAllEstudantesWithCurso().get();

        if(lista_dtos.size() > 0){
        
            for(EstudanteCursoDTO registro : lista_dtos){
                Integer idade_do_estudante = repository.findById(registro.getId()).get().getIdade();
                registro.setIdade(idade_do_estudante);
            }
        }

        return lista_dtos;
    }

    public EstudanteModel addEstudante(EstudanteModel estudante){
        Optional<EstudanteModel> emailExiste = repository.findByEmail(estudante.getEmail());

        if(emailExiste.isPresent()){
            throw new IllegalStateException("Email já cadastrado");
        } 
        
        return repository.save(estudante);
    }

    public String deletarEstudante(long id){
        try{
            repository.deleteById(id);
        } catch(Throwable e){
            return "Erro ao deletar: "+e;
        }

        return "Deletado com sucesso";
    }

    public EstudanteModel atualizarEstudante(long id, EstudanteModel new_estudante){
        Optional<EstudanteModel> estudanteExiste = repository.findById(id);
        Optional<EstudanteModel> emailExiste = repository.findByEmail(new_estudante.getEmail());

        String novo_email = new_estudante.getEmail();
        String novo_nome_estudante = new_estudante.getNome_estudante();
        LocalDate nova_data = new_estudante.getData_nasc();

        if(!estudanteExiste.isPresent()){
            throw new IllegalStateException("Estudante não existe");
        } 

        if(emailExiste.isPresent()){
            throw new IllegalStateException("Email já cadastrado");
        }

        if(novo_email.equals(null) || novo_nome_estudante.equals(null) || nova_data.equals(null)){
            throw new IllegalStateException("Valores invalidos");
        }

        EstudanteModel estudante = estudanteExiste.get();
        estudante.setNome_estudante(novo_nome_estudante);
        estudante.setEmail(novo_email);
        estudante.setData_nasc(nova_data);

        return repository.save(estudante);
    }
}
