package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Credor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

@Repository
public class CredorDAO extends GenericoDAO<Credor> {

    public CredorDAO(EntityManagerFactory emf) {
        super(emf, Credor.class);
    }

    public Credor buscarLogin(String CPF, String password) {
        try {
            comando = """
                    SELECT Credor FROM Credor Credor
                    WHERE Credor.CPF = :CPF
                    AND Credor.password = :password""";
            TypedQuery<Credor> query = entityManager.createQuery(comando, Credor.class);

            query.setParameter("CPF", CPF);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
