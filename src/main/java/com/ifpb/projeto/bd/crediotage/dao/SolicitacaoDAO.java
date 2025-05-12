package com.ifpb.projeto.bd.crediotage.dao;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import com.ifpb.projeto.bd.crediotage.model.Solicitacao;
import com.ifpb.projeto.bd.crediotage.model.Status;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public class SolicitacaoDAO extends GenericoDAO<Solicitacao> {

    public SolicitacaoDAO(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Solicitacao.class);
    }

    public List<Solicitacao> listarPorStatus(Status status, Proposta proposta) {
        comando = """
                SELECT solicitacao FROM Solicitacao solicitacao
                WHERE solicitacao.status =:status
                AND solicitacao.proposta =:fk_proposta""";

        if(status == Status.PENDENTE){
            comando +=  " AND solicitacao.cliente.emprestimo IS NULL";
        }

        try{
            this.entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Solicitacao> query = entityManager.createQuery(comando, Solicitacao.class);
            query.setParameter("status", status);
            query.setParameter("fk_proposta", proposta);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }


    public List<Solicitacao> buscarPorCliente(Cliente cliente) {
        comando = "SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.cliente = :fk_cliente";

        try {
            this.entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Solicitacao> query = entityManager.createQuery(comando, Solicitacao.class);
            query.setParameter("fk_cliente", cliente);
            return query.getResultList();
        }catch (Exception e){
           return null;
        }finally{
            entityManager.close();
        }

    }

    public Solicitacao buscarNaoNegados(Proposta proposta, Cliente cliente){
        try {
            this.entityManager = entityManagerFactory.createEntityManager();
            comando = """
                SELECT solicitacao FROM Solicitacao solicitacao
                WHERE solicitacao.proposta = :fk_proposta
                AND solicitacao.cliente = :fk_cliente AND NOT solicitacao.status = :status""";

            TypedQuery<Solicitacao> query = entityManager.createQuery(comando, Solicitacao.class);
            query.setParameter("fk_proposta", proposta);
            query.setParameter("fk_cliente",cliente);
            query.setParameter("status", Status.NEGADO);
            return query.getSingleResultOrNull();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Solicitacao atualizarStatus(UUID id, Status status) {
        try{
            this.entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Solicitacao solicitacao = entityManager.find(Solicitacao.class,id);
            solicitacao.setStatus(status);
            entityManager.getTransaction().commit();
            return solicitacao;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }finally {
            entityManager.close();
        }

    }
}
