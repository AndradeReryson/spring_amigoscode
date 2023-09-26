package com.amigoscode.spring_amigoscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.amigoscode.spring_amigoscode.services.EstudanteService;

@Controller
@RequestMapping("/")
public class ViewsController {

    String uri = "http://localhost:8080";

    @Autowired
    EstudanteService service;
    
    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("cadastro")
    public String cadastro(){
        return "cadastro";
    }

    @PostMapping("cadastro")
    public String dados_cadastro(@ModelAttribute("EstudanteModel") EstudanteModel estudante){  	
        try{
            service.addEstudante(estudante);
            return "redirect:/cadastro";
        } catch(Throwable e){
            throw new IllegalStateException("Erro ao processar dados");
        }
    }

    @GetMapping("listagem")
    public ModelAndView listagem(){
        ModelAndView mv = new ModelAndView("listagem");
       
        List<EstudanteCursoDTO> lista = service.getEstudantesWithCursos();
        


        mv.addObject("lista", lista);
        return mv;
    }
}
