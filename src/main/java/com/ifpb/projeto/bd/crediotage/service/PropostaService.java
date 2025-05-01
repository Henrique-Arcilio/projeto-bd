package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropostaService {
    private HttpSession session;
    private PropostaDAO propostaDAO;

    public PropostaService(HttpSession session, PropostaDAO propostaDAO) {
        this.session = session;
        this.propostaDAO = propostaDAO;
    }

    public void criarProposta(BigDecimal valorMaximo, BigDecimal juros, int parcelaMax) {
        Credor credor = (Credor) session.getAttribute("usuario");
        Proposta posposta = new Proposta(valorMaximo, juros, parcelaMax, credor);
        propostaDAO.salvar(posposta);
    }
    public List<Proposta> listar(){
        return propostaDAO.listar();
    }
}
