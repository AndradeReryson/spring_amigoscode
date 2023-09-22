package com.amigoscode.spring_amigoscode.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigoscode.spring_amigoscode.dtos.CursoDTO;
import com.amigoscode.spring_amigoscode.models.CursoModel;
import com.amigoscode.spring_amigoscode.repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository repository;

    public List<CursoDTO> getCursos(){
        List<CursoModel> lista_models = repository.findAll();
        List<CursoDTO> lista_dtos = new ArrayList<>();

        for(CursoModel curso : lista_models){
            CursoDTO dto = new CursoDTO(curso);
            lista_dtos.add(dto);
        }

        return lista_dtos;
    }

    public Optional<CursoDTO> getCursoEspecifico(long id){
        Optional<CursoModel> curso = repository.findById(id);

        CursoDTO dto = new CursoDTO(curso.get());
        Optional<CursoDTO> opt_dto = Optional.of(dto);

        return opt_dto;
    }

    public CursoModel addCurso(CursoModel curso){
        Optional<CursoModel> nomeExiste = repository.findByNome(curso.getNome_curso());

        if(nomeExiste.isPresent()){
            throw new IllegalStateException("Já existe um curso com esse nome");
        }

        return repository.save(curso);
    }

    public String deletarCurso(long id){
        try{
            repository.deleteById(id);
        } catch(Throwable e){
            return "Erro ao deetar: "+e;
        }

        return "Deletado com sucesso";
    }    

    public String atualizarCurso(long id, CursoModel new_curso){
        Optional<CursoModel> cursoExiste = repository.findById(id);
        Optional<CursoModel> nomeExiste = repository.findByNome(new_curso.getNome_curso());

        String novo_nome = new_curso.getNome_curso();
        String nova_desc = new_curso.getDescricao();
        BigDecimal nova_cargaHoraria = new_curso.getCarga_horaria();


        if(!cursoExiste.isPresent()){
            throw new IllegalStateException("Curso não existe");
        }

        if(nomeExiste.isPresent()){
            throw new IllegalStateException("Já existe um curso com esse nome");
        }

        if(novo_nome.equals(null) || nova_desc.equals(null) || nova_cargaHoraria.equals(null) ){
            throw new IllegalStateException("Existe algum campo vazio");
        }

        CursoModel curso_modificado = cursoExiste.get();
        curso_modificado.setNome_curso(novo_nome);
        curso_modificado.setDescricao(nova_desc);
        curso_modificado.setCarga_horaria(nova_cargaHoraria);

        repository.save(curso_modificado);
        return "Atualizado com sucesso";
    }
}
