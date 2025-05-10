package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.EmprestimoDAO;
import com.ifpb.projeto.bd.crediotage.dao.PropostaDAO;
import com.ifpb.projeto.bd.crediotage.dao.SolicitacaoDAO;
import com.ifpb.projeto.bd.crediotage.model.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO;
    private SolicitacaoDAO solicitacaoDAO;
    private PropostaDAO propostaDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, SolicitacaoDAO solicitacaoDAO, PropostaDAO propostaDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.solicitacaoDAO = solicitacaoDAO;
        this.propostaDAO = propostaDAO;
    }

    public Emprestimo buscarPorCliente(Cliente cliente){
        return emprestimoDAO.buscarPorCliente(cliente);
    }

    public void quitarEmprestimo(UUID idEmprestimo){
        Emprestimo emprestimo = emprestimoDAO.buscar(idEmprestimo);
        Solicitacao solicitacao = buscarSolicitacaoRelacionada(emprestimo);
        solicitacaoDAO.deletar(solicitacao.getId());
        emprestimoDAO.deletar(idEmprestimo);
    }

    public Solicitacao buscarSolicitacaoRelacionada(Emprestimo emprestimo){
        Cliente cliente = emprestimo.getCliente();
        Credor credor = emprestimo.getCredor();
        Proposta propostaDoCredor = propostaDAO.buscarPorCredor(credor);
        return solicitacaoDAO.buscarExistenteNaProposta(propostaDoCredor, cliente);
    }
}
