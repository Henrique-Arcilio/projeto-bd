package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.model.*;
import com.ifpb.projeto.bd.crediotage.service.EmprestimoService;
import com.ifpb.projeto.bd.crediotage.service.PropostaService;
import com.ifpb.projeto.bd.crediotage.service.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    private PropostaService propostaService;
    private SolicitacaoService solicitacaoService;
    private EmprestimoService emprestimoService;

    public HomeController(PropostaService propostaService, SolicitacaoService solicitacaoService, EmprestimoService emprestimoService) {
        this.propostaService = propostaService;
        this.solicitacaoService = solicitacaoService;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario instanceof Cliente cliente) { //citar pattern matching
            List<Solicitacao> solicitacoesDoCliente = solicitacaoService.buscarPorCliente();
            List<Proposta> propostas = propostaService.buscarNaoSolicitadas(cliente);
            Emprestimo emprestimo = emprestimoService.buscarPorCliente(cliente);

            model.addAttribute("propostas", propostas);
            model.addAttribute("solicitacoes", solicitacoesDoCliente);
            model.addAttribute("emprestimo", emprestimo);
            model.addAttribute("nomeCliente", usuario.getNome());
            return "home-page-cliente";

        }else if (usuario instanceof Credor credor){
            List<Solicitacao> solicitacoesPendentes = solicitacaoService.listarSolicitacoesPorStatus(Status.PENDENTE, credor);
            List<Solicitacao> solicitacoesAprovadas = solicitacaoService.listarSolicitacoesPorStatus(Status.APROVADO, credor);

            model.addAttribute("solicitacoesPendentes", solicitacoesPendentes);
            model.addAttribute("solicitacoesAprovadas", solicitacoesAprovadas);
            model.addAttribute("nomeCliente", usuario.getNome());

            return "home-page-credor";

        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/home/pagar")
    public String quitarEmprestimo(@RequestParam UUID idEmprestimo){
        emprestimoService.quitarEmprestimo(idEmprestimo);
        return "redirect:/home";
    }

    @PostMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
