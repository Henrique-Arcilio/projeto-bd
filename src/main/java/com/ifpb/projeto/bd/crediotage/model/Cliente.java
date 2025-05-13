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
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{

    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacoes;

    @OneToOne(mappedBy = "cliente", orphanRemoval = true)
    private Emprestimo emprestimo;

    public Cliente(String name, String email, String cpf, String password, String endereco, LocalDate dataNascimento){
       super(name, email, cpf, password, endereco, dataNascimento);
    }
}
