package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.UsuarioDAO;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UsuarioDAO usuarioDAO;
    
    public LoginService(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    public String validarLogin(String cpf, String password){
      Usuario usuario = usuarioDAO.buscarLogin(cpf, password);
      if(usuario instanceof Cliente){
        return "home-page-cliente";
      }else if( usuario instanceof Credor){
        return "home-page-credor";
      }
      return null;
    }
}
