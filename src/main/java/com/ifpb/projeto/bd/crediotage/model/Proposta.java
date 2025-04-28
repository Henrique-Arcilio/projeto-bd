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
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal valorMaximo;
    @Column(nullable = false)
    private BigDecimal juros;
    @Column(nullable = false)
    private int parcelasMaxima;
    @OneToOne
    @JoinColumn(name = "fk_credor")
    private Credor credor;

    public Proposta(BigDecimal valorMaximo, BigDecimal juros, int parcelasMaxima, Credor credor) {
        this.valorMaximo = valorMaximo;
        this.juros = juros;
        this.parcelasMaxima = parcelasMaxima;
        this.credor = credor;
    }

}
