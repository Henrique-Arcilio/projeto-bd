package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.service.PropostaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class PropostaController {
    private PropostaService service;

    public PropostaController(PropostaService service) {
        this.service = service;
    }

    @PostMapping("/home/criar-proposta")
    public String criarProposta( @RequestParam BigDecimal valorMaximo,
                                 @RequestParam BigDecimal juros, @RequestParam int parcelaMax) {
        service.criarProposta(valorMaximo, juros, parcelaMax);
        return "redirect:/home";
    }
}
