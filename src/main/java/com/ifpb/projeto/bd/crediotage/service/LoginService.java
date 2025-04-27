package com.ifpb.projeto.bd.crediotage.service;

import com.ifpb.projeto.bd.crediotage.dao.ClienteDAO;
import com.ifpb.projeto.bd.crediotage.dao.CredorDAO;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import com.ifpb.projeto.bd.crediotage.model.Usuario;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ClienteDAO clienteDAO;
    private CredorDAO credorDAO;

    public LoginService(CredorDAO credorDAO, ClienteDAO clienteDAO) {
        this.credorDAO = credorDAO;
        this.clienteDAO = clienteDAO;
    }

    public String validarLogin(String cpf, String password) {
        Usuario usuario;

        usuario = clienteDAO.buscarLogin(cpf, password);

        if (usuario == null) {
            usuario = credorDAO.buscarLogin(cpf, password);
        }

        if(usuario instanceof Cliente) {
            return "redirect:/home-page-cliente";
        }else if (usuario instanceof Credor){
            return "redirect:/home-page-credor";
        }else{
            return "redirect:/login";
        }
    }
}
