package com.ifpb.projeto.bd.crediotage.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class GenericoDAO<T> implements DAO<T>{

    protected final EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    private Class<T> classeDaEntidade; // É igual quando nós pega NomeDaClasse.class (ex.: Cliente.class)
    protected String comando;

    public GenericoDAO(EntityManagerFactory entityManagerFactory, Class<T> classeDaEntidade) {
        this.entityManagerFactory = entityManagerFactory;
        this.classeDaEntidade = classeDaEntidade;
    }

    @Override
    public List<T> listar() {
        comando = "SELECT entidade FROM " + classeDaEntidade.getSimpleName() + " entidade";

        try{
            this.entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<T> query = entityManager.createQuery(comando, classeDaEntidade);
            return query.getResultList();

        }catch (Exception e){
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void salvar(T entidade) {
        try{
            this.entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public T buscar(UUID id) {
        try{
            this.entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(classeDaEntidade, id);
        }catch (Exception e){
            return null;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void deletar(UUID idEntidade){
        try{
            entityManager.getTransaction().begin();
            T entidade = entityManager.find(classeDaEntidade, idEntidade);
            entityManager.remove(entidade);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }

    }
}
