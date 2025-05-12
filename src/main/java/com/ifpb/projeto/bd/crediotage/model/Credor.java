package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Credor")
public class Credor extends Usuario{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "credor", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "credor")
    private Proposta proposta;

    public Credor(String name, String email, String cpf, String password, String endereco, LocalDate dataNascimento){
       super(name, email, cpf, password, endereco, dataNascimento);
    }
}
