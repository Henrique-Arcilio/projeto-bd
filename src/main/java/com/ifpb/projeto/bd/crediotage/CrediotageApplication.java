package com.ifpb.projeto.bd.crediotage;

import com.ifpb.projeto.bd.crediotage.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
	
@SpringBootApplication
public class CrediotageApplication {

	public static void main(String[] args){
		SpringApplication.run(CrediotageApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crediotage");
	}

}
