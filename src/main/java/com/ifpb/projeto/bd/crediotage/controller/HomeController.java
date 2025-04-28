package com.ifpb.projeto.bd.crediotage.controller;

import com.ifpb.projeto.bd.crediotage.dao.ClienteDAO;
import com.ifpb.projeto.bd.crediotage.dao.CredorDAO;
import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private PropostaDAO propostaDAO;

    public HomeController(PropostaDAO propostaDAO) {
        this.propostaDAO = propostaDAO;
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Proposta> propostas = propostaDAO.listar();
        if(usuario instanceof Cliente) {
            model.addAttribute("propostas", propostas);
            return "home-page-cliente";

        }else if (usuario instanceof Credor){
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
