package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.model.Usuario;
import com.ifpb.projeto.bd.crediotage.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private LoginService service;

    public LoginController(LoginService service){
        this.service = service;
    }

    @PostMapping("/autenticar")
    public String validarLogin(@RequestParam String cpf, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.validarLogin(cpf, password);
            if(usuario != null){
                session.setAttribute("usuario", usuario);
                return "redirect:/home";
            } else {
                redirectAttributes.addFlashAttribute("erro", "CPF ou senha inválidos.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "CPF ou senha inválidos.");
        }

        return "redirect:/login";

    }
}
