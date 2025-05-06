package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.dao.SolicitacaoDAO;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import com.ifpb.projeto.bd.crediotage.model.Status;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
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

    public List<Solicitacao> buscarSolicitacaoPorCliente() {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        return solicitacaoDAO.buscarPorCliente(cliente);
    }

    public List<Solicitacao> listar(){
        return solicitacaoDAO.listar();
    }

    public void atualizarSolicitacao(List<UUID> idPropostas, boolean aprovado){
        Status status = Status.NEGADO;
        if(aprovado){
            status = Status.APROVADO;
        }
        for(UUID id: idPropostas){
            solicitacaoDAO.atualizarStatus(id, status);
        }
    }

    public List<Solicitacao> listarSolicitacoesAprovadas(){
        return solicitacaoDAO.listarPorStatus(Status.APROVADO);
    }

    public List<Solicitacao> listarSolicitacoesPendentes(){
        return solicitacaoDAO.listarPorStatus(Status.PENDENTE);
    }

}
