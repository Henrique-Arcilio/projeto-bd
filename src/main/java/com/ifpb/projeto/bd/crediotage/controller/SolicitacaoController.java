package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.service.PropostaService;
import com.ifpb.projeto.bd.crediotage.service.SolicitacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class SolicitacaoController {
    private SolicitacaoService service;
    private PropostaService propostaService;

    public SolicitacaoController(SolicitacaoService service, PropostaService propostaService) {
        this.service = service;
        this.propostaService = propostaService;
    }

    @GetMapping("/explorar/{id}/")
    public String explorar(@PathVariable UUID id, Model model) {
        Proposta proposta= propostaService.buscarPorId(id);
        model.addAttribute("proposta", proposta);
        return "solicitacao-home-page";
    }

    @PostMapping("/explorar/{id}/criar-solicitacao")
    public String criarSolicitacao(@RequestParam BigDecimal valor, @RequestParam LocalDate dataDePagamento,
                                   @PathVariable UUID id) {
        service.criarSolicitacao(valor, dataDePagamento, id);
        return "redirect:/home";
    }

    @PostMapping("/home/gerenciar-solicitacao")
    public String genrenciarSolicitacao(@RequestParam List<UUID> idSolicitacoes, @RequestParam boolean aprovado){
        service.atualizarSolicitacao(idSolicitacoes, aprovado);
        return "redirect:/home";
    }


}
