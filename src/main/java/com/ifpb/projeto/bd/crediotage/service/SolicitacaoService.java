package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.EmprestimoDAO;
import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.dao.SolicitacaoDAO;
import com.ifpb.projeto.bd.crediotage.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SolicitacaoService {
    private SolicitacaoDAO solicitacaoDAO;
    private PropostaDAO propostaDAO;
    private EmprestimoDAO emprestimoDAO;
    private HttpSession session;

    public SolicitacaoService(SolicitacaoDAO solicitacaoDAO, PropostaDAO propostaDAO, EmprestimoDAO emprestimoDAO, HttpSession session) {
        this.solicitacaoDAO = solicitacaoDAO;
        this.propostaDAO = propostaDAO;
        this.emprestimoDAO = emprestimoDAO;
        this.session = session;
    }

    public void criarSolicitacao(BigDecimal valor, LocalDate dataDePagamento, UUID idProposta) {
        Proposta proposta = propostaDAO.buscar(idProposta);
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        Solicitacao solicitacao = new Solicitacao(valor, dataDePagamento, cliente, proposta);
        solicitacaoDAO.salvar(solicitacao);
    }

    public List<Solicitacao> buscarSolicitacaoPorCliente() {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        return solicitacaoDAO.buscarPorCliente(cliente);
    }

    public List<Solicitacao> listar(){
        return solicitacaoDAO.listar();
    }

    public void atualizarSolicitacao(List<UUID> idSolicitacoes, boolean aprovado){
        Status status = Status.NEGADO;
        if(aprovado){
            status = Status.APROVADO;
        }
        for(UUID id : idSolicitacoes) {

            Solicitacao solicitacao = solicitacaoDAO.buscar(id);
            solicitacaoDAO.atualizarStatus(solicitacao, status);

            BigDecimal valor = solicitacao.getValorSolicitado();
            BigDecimal juros = solicitacao.getProposta().getJuros();
            LocalDate dataDePagamento = solicitacao.getDataDePagamento();
            Cliente cliente = solicitacao.getCliente();
            Credor credor = solicitacao.getProposta().getCredor();
            //sanar problema de emprestimos duplicados
            if (status == Status.APROVADO) {
                Emprestimo emprestimo = new Emprestimo(dataDePagamento, valor, juros, cliente, credor);
                emprestimoDAO.salvar(emprestimo);
            }
        }
    }

    public List<Solicitacao> listarSolicitacoesAprovadas(){
        return solicitacaoDAO.listarPorStatus(Status.APROVADO);
    }

    public List<Solicitacao> listarSolicitacoesPendentes(){
        return solicitacaoDAO.listarPorStatus(Status.PENDENTE);
    }

}
