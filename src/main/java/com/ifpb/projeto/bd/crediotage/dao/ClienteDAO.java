package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClienteDAO implements DAO<Cliente>{
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cliente> listar() {
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT cliente FROM Cliente cliente", Cliente.class);
        return query.getResultList();
    }

    @Override
    public void salvar(Cliente cliente) {
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    @Override
    public void atualizar(Cliente cliente, String[] valores) {
        // aidna vou fazer
    }

    @Override
    public void deletar(Cliente cliente) {
        entityManager.getTransaction().begin();
        Cliente managedCliente = entityManager.merge(cliente);
        entityManager.remove(managedCliente);
        entityManager.getTransaction().commit();
    }

    public Cliente buscarLogin(String CPF, String password) {
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT Cliente FROM Cliente Cliente WHERE Cliente.CPF = :CPF AND Cliente.password = :password", Cliente.class);
            
            query.setParameter("CPF", CPF);
            query.setParameter("password", password);
            
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
