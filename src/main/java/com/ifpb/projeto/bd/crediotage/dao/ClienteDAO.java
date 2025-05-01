package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO implements DAO<Cliente>{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public ClienteDAO(EntityManagerFactory emf) {
        this.emf = emf;
        entityManager = emf.createEntityManager();
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
    public Cliente buscarPorId(UUID id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public void atualizar(Cliente cliente, String[] valores) {

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
