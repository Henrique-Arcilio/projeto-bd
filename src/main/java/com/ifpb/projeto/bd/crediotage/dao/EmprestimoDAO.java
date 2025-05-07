package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        return entityManager.find(Emprestimo.class, id);
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
    public void deletarPorId(UUID idEmprestimo){
        entityManager.getTransaction().begin();
        Emprestimo emprestimo = entityManager.find(Emprestimo.class, idEmprestimo);
        entityManager.remove(emprestimo);
        entityManager.getTransaction().commit();
    }
}
