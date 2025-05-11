package com.ifpb.projeto.bd.crediotage.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@DiscriminatorColumn(name="Tipo", discriminatorType= DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable=false)
    private String email;
    @Column(nullable = false, length = 11)
    private String CPF;
    @Column(nullable = false, length = 22)
    private String password;
    @Column(nullable = false)
    private String endereco;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, String CPF, String password, String endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.password = password;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }
}
