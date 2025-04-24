package com.ifpb.projeto.bd.crediotage.model;

import java.util.List;

public class Cliente {
    private Score score;
    private List<Solicitacao> solicitacoes;

    @OneToOne(mappedBy = "cliente")
    private Emprestimo emprestimo;
}
