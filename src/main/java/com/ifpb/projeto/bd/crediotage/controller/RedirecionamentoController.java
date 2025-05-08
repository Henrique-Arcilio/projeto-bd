package com.ifpb.projeto.bd.crediotage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirecionamentoController {

    @GetMapping("/")
    public String redirectLogin(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login-page";
    }

    @GetMapping("/cadastro")
    public String showCadastro() {
        return "cadastro-page";
    }
}
