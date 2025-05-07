package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.EmprestimoDAO;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Emprestimo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmprestimoService {
    private EmprestimoDAO emprestimoDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO) {
        this.emprestimoDAO = emprestimoDAO;
    }


    public Emprestimo buscarPorCliente(Cliente cliente){
        return emprestimoDAO.buscarPorCliente(cliente);
    }

    public void pagarEmprestimo(UUID idEmprestimo){
        emprestimoDAO.deletarPorId(idEmprestimo);
    }
}
