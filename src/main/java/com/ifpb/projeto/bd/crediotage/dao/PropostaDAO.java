package com.ifpb.projeto.bd.crediotage.dao;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class PropostaDAO extends GenericoDAO<Proposta> {
    public PropostaDAO(EntityManagerFactory emf) {
        super(emf, Proposta.class);
    }
}
