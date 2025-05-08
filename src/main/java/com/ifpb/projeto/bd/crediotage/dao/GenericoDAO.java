package com.ifpb.projeto.bd.crediotage.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class GenericoDAO<T> implements DAO<T>{

    private EntityManagerFactory emf;
    protected EntityManager entityManager;
    private Class<T> classeDaEntidade; // É igual quando nós pega NomeDaClasse.class (ex.: Cliente.class)

    public GenericoDAO(EntityManagerFactory emf, Class<T> classeDaEntidade) {
        this.emf = emf;
        this.entityManager = emf.createEntityManager();
        this.classeDaEntidade = classeDaEntidade;
    }

    @Override
    public List<T> listar() {
        String comando = "SELECT entidade FROM " + classeDaEntidade.getSimpleName() + " entidade";
        TypedQuery<T> query = entityManager.createQuery(comando, classeDaEntidade);
        return query.getResultList();
    }

    @Override
    public void salvar(T entidade) {
        entityManager.getTransaction().begin();
        entityManager.persist(entidade);
        entityManager.getTransaction().commit();
    }

    @Override
    public T buscar(UUID id) {
        return entityManager.find(classeDaEntidade, id);
    }

    @Override
    public void deletar(UUID idEntidade){
        entityManager.getTransaction().begin();
        T entidade = entityManager.find(classeDaEntidade, idEntidade);
        entityManager.remove(entidade);
        entityManager.getTransaction().commit();
    }
}
