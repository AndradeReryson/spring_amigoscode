package com.amigoscode.spring_amigoscode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.amigoscode.spring_amigoscode.dtos.EstudanteCursoDTO;
import com.amigoscode.spring_amigoscode.models.EstudanteModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class ViewsController {

    String uri = "http://localhost:8080";

    @Autowired
    ObjectMapper mapper;

    @Autowired
    RestTemplate restTemplate;
    
    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("cadastro")
    public String cadastro(){
        return "cadastro";
    }

    @PostMapping(path = "cadastro")
    public String dados_cadastro(@ModelAttribute("EstudanteModel") EstudanteModel estudante){  	
        try{
            String dados = restTemplate.postForObject(uri+"/api/v1/estudante", estudante, String.class);
            return "redirect:/cadastro";
        } catch(Throwable e){
            throw new IllegalStateException("Erro ao processar dados");
        }

        
    }

    @GetMapping("listagem")
    public ModelAndView listagem(){
        ModelAndView mv = new ModelAndView("listagem");
       
        EstudanteCursoDTO[] lista = restTemplate.getForObject(uri+"/api/v1/estudante/estudanteComCurso", 
                                                                    EstudanteCursoDTO[].class);   

        mv.addObject("lista", lista);
        return mv;
    }
}
