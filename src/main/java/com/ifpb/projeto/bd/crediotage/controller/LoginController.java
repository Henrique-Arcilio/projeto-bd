package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.service.CadastroService;
import com.ifpb.projeto.bd.crediotage.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private LoginService service;

    public LoginController(LoginService service){
        this.service = service;
    }

    @PostMapping("/autenticar")
    public String validarLogin(@RequestParam String cpf, @RequestParam String password){

        return service.validarLogin(cpf, password);
    }
}
