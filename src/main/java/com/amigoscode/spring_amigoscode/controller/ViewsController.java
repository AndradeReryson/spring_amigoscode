package com.amigoscode.spring_amigoscode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
        String estu = restTemplate.postForObject(uri+"/api/v1/estudante", estudante, String.class);

        return "redirect:/cadastro";
    }

    @GetMapping("listagem")
    public String listagem(){
        return "listagem";
    }
}
