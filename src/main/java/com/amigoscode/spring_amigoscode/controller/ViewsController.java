package com.amigoscode.spring_amigoscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amigoscode.spring_amigoscode.dtos.CursoDTO;
import com.amigoscode.spring_amigoscode.dtos.EstudanteBasicoDTO;
import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.amigoscode.spring_amigoscode.services.CursoService;
import com.amigoscode.spring_amigoscode.services.EstudanteService;

@Controller
@RequestMapping("/")
public class ViewsController {

    String uri = "http://localhost:8080";

    @Autowired
    EstudanteService estudanteService;
    
    @Autowired
    CursoService cursoService;

    @GetMapping
    public String home(){
        return "home";
    }

    /* ESTUDANTE */

    @GetMapping("cadastro/estudante")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("EstudanteCadastro");

        List<CursoDTO> lista = cursoService.getCursos();

        mv.addObject("cursos", lista);
        return mv;
    }

    @PostMapping("cadastro/estudante")
    public String cadastrar_estudante(@ModelAttribute("EstudanteModel") EstudanteModel estudante){  	
        try{
            estudanteService.addEstudante(estudante);
            return "redirect:/consulta/estudantes";
        } catch(Throwable e){
            throw new IllegalStateException("Erro ao processar dados:\n"+e);
        }
    }

    @GetMapping("consulta/estudantes")
    public ModelAndView listar_estudantes(){
        ModelAndView mv = new ModelAndView("EstudanteListagem");
       
        List<EstudanteCursoDTO> lista = estudanteService.getEstudantesWithCursos();
        
        mv.addObject("lista", lista);
        return mv;
    }

    @GetMapping("consulta/estudantes/{id}")
    public String deletar_estudante(@PathVariable("id") Long id){
        try{
            estudanteService.deletarEstudante(id);
            return "redirect:/consulta/estudantes";
        } catch(Throwable e){
            throw new IllegalStateException("Erro ao processar dados:\n"+e);
        }
    }
    
    //* PRoblemas abaixo

    @GetMapping("consulta/estudantes/atualizar/{id}")
    public ModelAndView enviar_form_de_atualizacao(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("EstudanteAtualizacao");

        EstudanteModel estudante = estudanteService.getEstudanteModelEspecifico(id);
        mv.addObject("estudante", estudante);
        
        List<CursoDTO> cursos = cursoService.getCursos();
        mv.addObject("cursos", cursos);

        for(CursoDTO curso : cursos) {
        	if(curso.getId().equals(estudante.getCurso().getId())) {
        		mv.addObject("curso_atual", curso);
        	}
        }
        
        return mv;
    }

    @PostMapping("consulta/estudantes/atualizar")
    public String atualizar_estudante(@ModelAttribute("EstudanteModel") EstudanteModel estudante){
        try{
            estudanteService.atualizarEstudante(estudante);
            return "redirect:/consulta/estudantes";
        } catch(Throwable e){
            throw new IllegalStateException("Erro ao atualizar dados:\n"+e);
        }
    }
}
