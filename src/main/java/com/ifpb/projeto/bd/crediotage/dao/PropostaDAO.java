package com.ifpb.projeto.bd.crediotage.dao;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropostaDAO implements DAO<Proposta> {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public PropostaDAO(EntityManagerFactory emf) {
        this.emf = emf;
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Proposta> listar() {
        TypedQuery<Proposta> query = entityManager.createQuery("SELECT proposta FROM Proposta proposta", Proposta.class);
        return query.getResultList();
    }

    @Override
    public void salvar(Proposta proposta) {
        entityManager.getTransaction().begin();
        entityManager.persist(proposta);
        entityManager.getTransaction().commit();
    }

    @Override
    public void atualizar(Proposta proposta, String[] valores) {

    }

    @Override
    public void deletar(Proposta proposta) {

    }
}
