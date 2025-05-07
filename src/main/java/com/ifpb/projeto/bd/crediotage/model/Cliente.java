package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import java.util.List;

@Entity
@Data
@Table(name="cliente")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Score score;

    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacoes;

    @OneToOne(mappedBy = "cliente",cascade = CascadeType.ALL)
    private Emprestimo emprestimo;

    public Cliente(String name, String email, String cpf, String password, String endereco, LocalDate dataNascimento){
       super(name, email, cpf, password, endereco, dataNascimento);
    }
}
