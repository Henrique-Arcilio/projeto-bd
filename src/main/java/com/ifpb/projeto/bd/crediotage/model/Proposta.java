package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    private LocalDate dataLimite;

    @OneToOne
    @JoinColumn(name = "fk_credor")
    private Credor credor;
    @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL)
    private List<Solicitacao> solicitacoes;

    public Proposta(BigDecimal valorMaximo, BigDecimal juros, LocalDate dataLimite, Credor credor) {
        this.valorMaximo = valorMaximo;
        this.juros = juros;
        this.dataLimite = dataLimite;
        this.credor = credor;
    }

}
