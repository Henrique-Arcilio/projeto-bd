package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CredorDAO implements DAO<Credor> {
    private EntityManager entityManager;

    public CredorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Credor> listar() {
        TypedQuery<Credor> query = entityManager.createQuery("SELECT Credor FROM Credor Credor", Credor.class);
        return query.getResultList();
    }

    @Override
    public void salvar(Credor credor) {
        entityManager.getTransaction().begin();
        entityManager.persist(credor);
        entityManager.getTransaction().commit();
    }

    @Override
    public void atualizar(Credor credor, String[] valores) {
        // aidna vou fazer
    }

    @Override
    public void deletar(Credor credor) {
        entityManager.getTransaction().begin();
        Credor managedCliente = entityManager.merge(credor);
        entityManager.remove(managedCliente);
        entityManager.getTransaction().commit();
    }

    public Credor buscarLogin(String CPF, String password) {
        try {
            TypedQuery<Credor> query = entityManager.createQuery("SELECT Credor FROM Credor Credor WHERE Credor.CPF = :CPF AND Credor.password = :password", Credor.class);

            query.setParameter("CPF", CPF);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
