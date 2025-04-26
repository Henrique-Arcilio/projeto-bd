package com.ifpb.projeto.bd.crediotage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getMethodName() {
        return "cadastro-page";
    }

    @GetMapping("/home")
    public String redirectCredor(String tipo){
        if (tipo.equals(tipo)){
            return "home-page-credor";
        } else {
            return "home-page-cliente";
        }
    }


}
