package com.ifpb.projeto.bd.crediotage.dao;

import org.springframework.stereotype.Repository;

import com.ifpb.projeto.bd.crediotage.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class UsuarioDAO {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public UsuarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
        this.entityManager = emf.createEntityManager();
    }

    
    public Usuario buscarLogin(String CPF, String password) {
        try {
            TypedQuery<Usuario> query = entityManager.createQuery("SELECT Usuario FROM Usuario Usuario WHERE Usuario.CPF = :CPF AND Usuario.password = :password", Usuario.class);
            
            query.setParameter("CPF", CPF);
            query.setParameter("password", password);
            
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
