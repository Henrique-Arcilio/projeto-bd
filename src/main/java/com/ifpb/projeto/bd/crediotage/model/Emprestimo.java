package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataSolicitacao;
    @Column(nullable = false)
    private LocalDate dataPrazoFinal;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private BigDecimal juros;
    @OneToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "fk_credor")
    private Credor credor;

    public Emprestimo(LocalDate dataPrazoFinal, BigDecimal valor, BigDecimal juros, Cliente cliente, Credor credor) {
        this.dataSolicitacao = LocalDate.now();
        this.dataPrazoFinal = dataPrazoFinal;
        this.valor = valor;
        this.juros = juros;
        this.cliente = cliente;
        this.credor = credor;
    }
}
