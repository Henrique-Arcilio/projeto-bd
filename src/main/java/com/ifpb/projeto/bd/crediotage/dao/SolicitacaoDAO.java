package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import com.ifpb.projeto.bd.crediotage.model.Status;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolicitacaoDAO extends GenericoDAO<Solicitacao> {
    private EntityManagerFactory emf;

    public SolicitacaoDAO(EntityManagerFactory emf) {
        super(emf, Solicitacao.class);
    }

    public List<Solicitacao> listarPorStatus(Status status) {
        TypedQuery<Solicitacao> query = entityManager.createQuery("SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.status = :status", Solicitacao.class);
        query.setParameter("status", status);
        return query.getResultList();
    }


    public List<Solicitacao> buscarPorCliente(Cliente cliente) {
        TypedQuery<Solicitacao> query = entityManager.createQuery("SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.cliente = :fk_cliente", Solicitacao.class);
        query.setParameter("fk_cliente", cliente);
        return query.getResultList();
    }

    public Solicitacao buscarPorProsposta(Proposta proposta, Cliente cliente){
        TypedQuery<Solicitacao> query = entityManager.createQuery("SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.proposta = :fk_proposta AND solicitacao.cliente = :fk_cliente", Solicitacao.class);
        query.setParameter("fk_proposta", proposta);
        query.setParameter("fk_cliente",cliente);
        return query.getSingleResultOrNull();
    }

    public void atualizarStatus(Solicitacao solicitacao, Status status) {
        entityManager.getTransaction().begin();
        solicitacao.setStatus(status);
        entityManager.getTransaction().commit();
    }
}
