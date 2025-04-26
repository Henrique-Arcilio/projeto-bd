package com.ifpb.projeto.bd.crediotage.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifpb.projeto.bd.crediotage.service.CadastroService;

@Controller
public class CadastroController {
    
    private CadastroService service;

    public CadastroController(CadastroService service){
        this.service = service;
    }

    @PostMapping("/save")
    public String redirectPath(@RequestParam String name, @RequestParam String email, @RequestParam String cpf,
                                @RequestParam String password, @RequestParam String endereco, @RequestParam LocalDate dataNascimento, @RequestParam String tipo){
        System.out.println(tipo);                   
        System.out.println(password);         
        service.criarUsuario(name, email, cpf, password, endereco, dataNascimento, tipo);                
        return  "redirect:/login";
    }
    
}
