package com.ifpb.projeto.bd.crediotage.dao;

import java.util.List;
import java.util.UUID;

public interface DAO <T> {
    List<T> listar();

    T buscar(UUID id);

    void salvar(T t);

    void deletar(UUID id);
}
