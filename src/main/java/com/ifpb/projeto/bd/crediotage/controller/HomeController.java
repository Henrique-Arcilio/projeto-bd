package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.model.*;
import com.ifpb.projeto.bd.crediotage.service.PropostaService;
import com.ifpb.projeto.bd.crediotage.service.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class HomeController {
    private PropostaService propostaService;
    private SolicitacaoService solicitacaoService;

    public HomeController(PropostaService propostaService, SolicitacaoService solicitacaoService) {
        this.propostaService = propostaService;
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario instanceof Cliente) {
            List<Solicitacao> solicitacoesDoCliente = solicitacaoService.buscarSolicitacaoPorCliente();
            List<Proposta> propostas = propostaService.listar();
            model.addAttribute("propostas", propostas);
            model.addAttribute("solicitacoes", solicitacoesDoCliente);
            return "home-page-cliente";

        }else if (usuario instanceof Credor){
            List<Solicitacao> todasSolicitacoes = solicitacaoService.listarSolicitacoesPendentes();
            model.addAttribute("solicitacoes", todasSolicitacoes);

            List<Solicitacao> solicitacoesAprovadas = solicitacaoService.listarSolicitacoesAprovadas();
            model.addAttribute("solicitacoesAprovadas", solicitacoesAprovadas);

            return "home-page-credor";

        }else{
            return "redirect:/login";
        }
    }
    @PostMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
