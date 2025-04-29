package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class SolicitacaoDAO implements DAO<Solicitacao> {
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public SolicitacaoDAO(EntityManagerFactory emf) {
        this.emf = emf;
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Solicitacao> listar() {
        TypedQuery<Solicitacao> query = entityManager.createQuery("SELECT solicitacao FROM Solicitacao solicitacao", Solicitacao.class);
        return query.getResultList();
    }

    @Override
    public void salvar(Solicitacao solicitacao) {
        entityManager.getTransaction().begin();
        entityManager.persist(solicitacao);
        entityManager.getTransaction().commit();
    }

    @Override
    public void atualizar(Solicitacao solicitacao, String[] valores) {

    }

    @Override
    public void deletar(Solicitacao solicitacao) {

    }
}
