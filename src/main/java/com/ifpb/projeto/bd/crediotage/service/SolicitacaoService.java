package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.ClienteDAO;
import com.ifpb.projeto.bd.crediotage.dao.CredorDAO;
import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.dao.SolicitacaoDAO;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SolicitacaoService {
    private SolicitacaoDAO solicitacaoDAO;
    private PropostaDAO propostaDAO;
    private HttpSession session;

    public SolicitacaoService(SolicitacaoDAO solicitacaoDAO, PropostaDAO propostaDAO, HttpSession session) {
        this.solicitacaoDAO = solicitacaoDAO;
        this.propostaDAO = propostaDAO;
        this.session = session;
    }

    public void criarSolicitacao(BigDecimal valor, int parcelas, UUID idProposta) {
        Proposta proposta = propostaDAO.buscarPorId(idProposta);
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        Solicitacao solicitacao = new Solicitacao(valor, parcelas, cliente, proposta);
        solicitacaoDAO.salvar(solicitacao);
    }
}
