package com.ifpb.projeto.bd.crediotage.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@Service
public class CadastroService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void criarUsuario(String name, String email, String cpf, String password, String endereco,LocalDate dataNascimento, String tipo){
        
        Usuario usuario;
        if(tipo.equals("Cliente")){
            usuario = new Cliente(name,email, cpf, password, endereco, dataNascimento);
        } else{
            usuario = new Credor(name, email, cpf, password, endereco, dataNascimento);
        }
        
        emf = Persistence.createEntityManagerFactory("crediotage");
        em = emf.createEntityManager();
        EntityTransaction transacao = em.getTransaction();
        
        transacao.begin();
        em.persist(usuario);
        transacao.commit();
        em.close();
        emf.close();
    }
}
