package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.*;
import com.ifpb.projeto.bd.crediotage.model.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO;
    private SolicitacaoDAO solicitacaoDAO;
    private CredorDAO credorDAO;
    private ClienteDAO clienteDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, SolicitacaoDAO solicitacaoDAO, CredorDAO credorDAO, ClienteDAO clienteDAO) {
        this.emprestimoDAO = emprestimoDAO;
        this.solicitacaoDAO = solicitacaoDAO;
        this.credorDAO = credorDAO;
        this.clienteDAO = clienteDAO;
    }

    public Emprestimo buscarPorCliente(Cliente cliente){
        return emprestimoDAO.buscarPorCliente(cliente);
    }

    public void quitarEmprestimo(UUID idEmprestimo){

        Emprestimo emprestimo = emprestimoDAO.buscar(idEmprestimo);
        Credor credor = emprestimo.getCredor();
        Cliente cliente = emprestimo.getCliente();

        cliente.setEmprestimo(null);
        credor.getEmprestimos().remove(emprestimo);
        credorDAO.salvar(credor);
        clienteDAO.salvar(cliente);

        Solicitacao solicitacao = solicitacaoDAO.buscarNaoNegados(credor.getProposta(), cliente);
        UUID idSolicitacao = solicitacao.getId();
        solicitacaoDAO.deletar(idSolicitacao);
    }

}
