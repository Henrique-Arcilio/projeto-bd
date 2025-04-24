package com.ifpb.projeto.bd.crediotage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="credor")
@Getter
@Setter
public class Credor extends Usuario{
    @OneToMany(mappedBy = "credor")
    private List<Emprestimo> emprestimo;
}
