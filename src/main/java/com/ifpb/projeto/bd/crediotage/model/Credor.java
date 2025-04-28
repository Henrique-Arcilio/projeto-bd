package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import java.util.List;

@Entity
@Table(name="credor")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Credor")

public class Credor extends Usuario{

    @OneToMany(mappedBy = "credor")
    private List<Emprestimo> emprestimo;

    @OneToOne(mappedBy = "credor")
    private Proposta proposta;

    public Credor(String name, String email, String cpf, String password, String endereco, LocalDate dataNascimento){
       super(name, email, cpf, password, endereco, dataNascimento);
    }
}
