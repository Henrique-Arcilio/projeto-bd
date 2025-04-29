package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal valorSolicitado;
    private int parcelas;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "fk_proposta")
    private Proposta proposta;

    public Solicitacao(BigDecimal valorSolicitado, int parcelas, Cliente cliente, Proposta proposta) {
        this.valorSolicitado = valorSolicitado;
        this.parcelas = parcelas;
        this.cliente = cliente;
        this.proposta = proposta;
    }
}
