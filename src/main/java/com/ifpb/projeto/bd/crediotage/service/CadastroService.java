package com.ifpb.projeto.bd.crediotage.service;

import java.time.LocalDate;

import com.ifpb.projeto.bd.crediotage.dao.ClienteDAO;
import com.ifpb.projeto.bd.crediotage.dao.CredorDAO;
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

        emf = Persistence.createEntityManagerFactory("crediotage");
        em = emf.createEntityManager();


        if(tipo.equals("cliente")){
            Cliente cliente = new Cliente(name,email, cpf, password, endereco, dataNascimento);
            ClienteDAO c = new ClienteDAO(em);
            c.salvar(cliente);
        } else if(tipo.equals("credor")){
            Credor credor = new Credor(name, email, cpf, password, endereco, dataNascimento);
            CredorDAO c = new CredorDAO(em);
            c.salvar(credor);
        }

        em.close();
        emf.close();
    }


}
