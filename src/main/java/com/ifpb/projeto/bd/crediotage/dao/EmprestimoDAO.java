package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Emprestimo;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


@Repository
public class EmprestimoDAO extends GenericoDAO<Emprestimo>{

    public EmprestimoDAO(EntityManagerFactory emf) {
        super(emf, Emprestimo.class);
    }
    public Emprestimo buscarPorCliente(Cliente cliente){
        TypedQuery<Emprestimo> query = entityManager.createQuery("SELECT Emprestimo FROM Emprestimo Emprestimo where Emprestimo.cliente = :fk_cliente", Emprestimo.class);
        query.setParameter("fk_cliente", cliente);
        try{
            return query.getSingleResult();
        }
        catch (Exception e ){
            return null;
        }
    }
}
