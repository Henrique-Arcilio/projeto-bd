package com.ifpb.projeto.bd.crediotage;

import com.ifpb.projeto.bd.crediotage.model.Credor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;


@SpringBootApplication
public class CrediotageApplication {

	public static void main(String[] args){
		SpringApplication.run(CrediotageApplication.class, args);
	}

}
