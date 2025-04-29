package com.ifpb.projeto.bd.crediotage.controller;


import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import com.ifpb.projeto.bd.crediotage.service.SolicitacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.UUID;

@Controller
public class SolicitacaoHome {
    private SolicitacaoService service;
    private PropostaDAO propostaDAO;

    public SolicitacaoHome(SolicitacaoService service, PropostaDAO propostaDAO) {
        this.service = service;
        this.propostaDAO = propostaDAO;
    }

    @GetMapping("/explorar/{id}/")
    public String explorar(@PathVariable UUID id, Model model) {
        Proposta proposta = propostaDAO.buscarPorId(id);
        model.addAttribute("proposta", proposta);
        return "solicitacao-home-page";
    }
    @PostMapping("/explorar/{id}/criar-solicitacao")
    public String criarSolicitacao(@RequestParam BigDecimal valor, @RequestParam int parcelas,
                                   @PathVariable UUID id, Model model) {

        service.criarSolicitacao(valor, parcelas, id);
        return "redirect:/home";
    }
}
