package com.ifpb.projeto.bd.crediotage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
public class FactoryConfiguration {
    @Bean
    public EntityManagerFactory retornaFactory(){
        return Persistence.createEntityManagerFactory("crediotage");
    }
}
