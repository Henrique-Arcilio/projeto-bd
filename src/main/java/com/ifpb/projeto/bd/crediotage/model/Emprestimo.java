package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataSolicitacao;
    @Column(nullable = false)
    private LocalDate dataPrazoFinal;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private BigDecimal juros;
    @Column(nullable = false)
    private int parcelas;

    @OneToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_credor")
    private Credor credor;
}
