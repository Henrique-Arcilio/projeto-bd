package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EmprestimoDAO implements DAO<Emprestimo>{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public EmprestimoDAO(EntityManagerFactory emf) {
        this.emf = emf;
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Emprestimo> listar() {
        return List.of();
    }

    @Override
    public Emprestimo buscarPorId(UUID id) {
        return null;
    }

    @Override
    public void salvar(Emprestimo emprestimo) {
        entityManager.getTransaction().begin();
        entityManager.persist(emprestimo);
        entityManager.getTransaction().commit();
    }

    @Override
    public void atualizar(Emprestimo emprestimo, String[] valores) {

    }

    @Override
    public void deletar(Emprestimo emprestimo) {

    }
}
