package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO extends GenericoDAO<Cliente>{

    public ClienteDAO(EntityManagerFactory emf) {
        super(emf, Cliente.class);
    }

    public Cliente buscarLogin(String CPF, String password) {
        comando = """
                SELECT Cliente FROM Cliente Cliente
                WHERE Cliente.CPF = :CPF
                AND Cliente.password = :password""";
        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Cliente> query = entityManager.createQuery(comando, Cliente.class);
            query.setParameter("CPF", CPF);
            query.setParameter("password", password);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        finally {
            entityManager.close();
        }
    }
}
