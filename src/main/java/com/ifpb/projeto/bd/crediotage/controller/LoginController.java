package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.model.Usuario;
import com.ifpb.projeto.bd.crediotage.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private LoginService service;

    public LoginController(LoginService service){
        this.service = service;
    }

    @PostMapping("/autenticar")
    public String validarLogin(@RequestParam String cpf, @RequestParam String password, HttpSession session){
        Usuario usuario = service.validarLogin(cpf, password);
        if(usuario != null){
            session.setAttribute("usuario", usuario);
            return "redirect:/home";
        }
        return "redirect:/login";
    }
}
