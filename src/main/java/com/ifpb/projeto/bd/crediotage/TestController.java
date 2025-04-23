package com.ifpb.projeto.bd.crediotage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String testando(){
        System.out.println("entrou aqui");
        return "login-page";
    }
}
