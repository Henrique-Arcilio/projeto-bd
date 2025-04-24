package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cliente")
public class Cliente extends Usuario{

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Score score;

    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacoes;

    @OneToOne(mappedBy = "cliente")
    private Emprestimo emprestimo;
}
