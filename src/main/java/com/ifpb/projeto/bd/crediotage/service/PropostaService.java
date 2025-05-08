package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PropostaService {
    private HttpSession session;
    private PropostaDAO propostaDAO;

    public PropostaService(HttpSession session, PropostaDAO propostaDAO) {
        this.session = session;
        this.propostaDAO = propostaDAO;
    }

    public void criarProposta(BigDecimal valorMaximo, BigDecimal juros, LocalDate dataLimite) {
        Credor credor = (Credor) session.getAttribute("usuario");
        Proposta posposta = new Proposta(valorMaximo, juros, dataLimite, credor);
        propostaDAO.salvar(posposta);
    }

    public Proposta buscarPorId(UUID id) {
        return propostaDAO.buscar(id);
    }

    public List<Proposta> listar(){
        return propostaDAO.listar();
    }


}
