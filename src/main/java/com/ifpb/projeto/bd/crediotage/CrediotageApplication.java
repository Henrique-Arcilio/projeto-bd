package com.ifpb.projeto.bd.crediotage;

import com.ifpb.projeto.bd.crediotage.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class CrediotageApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CrediotageApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crediotage");
		EntityManager em = emf.createEntityManager();
		Usuario usuario = new Usuario("Yasmin", "123123123", "senha", "endereco", LocalDate.now());
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		emf.close();
		em.close();
	}

}
