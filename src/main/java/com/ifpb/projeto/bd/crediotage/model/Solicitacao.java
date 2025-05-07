package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate dataDePagamento;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "fk_proposta")
    private Proposta proposta;

    public Solicitacao(BigDecimal valorSolicitado, LocalDate dataDePagamento, Cliente cliente, Proposta proposta) {
        this.valorSolicitado = valorSolicitado;
        this.dataDePagamento = dataDePagamento;
        this.cliente = cliente;
        this.proposta = proposta;
        this.status = Status.PENDENTE;
    }
}
