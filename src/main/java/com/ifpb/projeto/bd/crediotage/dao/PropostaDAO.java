package com.ifpb.projeto.bd.crediotage.dao;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Proposta;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


@Repository
public class PropostaDAO extends GenericoDAO<Proposta> {
    public PropostaDAO(EntityManagerFactory emf) {
        super(emf, Proposta.class);
    }
    public Proposta buscarPorCredor(Credor credor){
        comando = "SELECT proposta FROM Proposta proposta WHERE proposta.credor = :fk_credor";
        TypedQuery<Proposta> query = entityManager.createQuery(comando, Proposta.class);
        query.setParameter("fk_credor", credor);
        return query.getSingleResultOrNull();
    }
}
