package com.ifpb.projeto.bd.crediotage.service;

import java.time.LocalDate;

import com.ifpb.projeto.bd.crediotage.dao.ClienteDAO;
import com.ifpb.projeto.bd.crediotage.dao.CredorDAO;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;
import com.ifpb.projeto.bd.crediotage.model.Cliente;
import com.ifpb.projeto.bd.crediotage.model.Credor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class CadastroService {

    private ClienteDAO clienteDAO;
    private CredorDAO credorDAO;


    public CadastroService(ClienteDAO clienteDAO, CredorDAO credorDAO) {
        this.clienteDAO = clienteDAO;
        this.credorDAO = credorDAO;
    }

    public void criarUsuario(String name, String email, String cpf, String password, String endereco, LocalDate dataNascimento, String tipo) throws Exception{
        if(tipo.equals("cliente") && clienteDAO.buscarPorCPF(cpf) == null){
            Cliente cliente = new Cliente(name,email, cpf, password, endereco, dataNascimento);
            clienteDAO.salvar(cliente);

        } else if(tipo.equals("credor") && clienteDAO.buscarPorCPF(cpf) == null ){
            Credor credor = new Credor(name, email, cpf, password, endereco, dataNascimento);
            credorDAO.salvar(credor);
        } else {
            throw new Exception("CPF já cadastrado");
        }
    }

}
