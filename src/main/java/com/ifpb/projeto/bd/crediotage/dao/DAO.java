package com.ifpb.projeto.bd.crediotage.dao;

import java.util.List;
import java.util.UUID;

public interface DAO <T> {
    List<T> listar();

    T buscarPorId(UUID id);

    void salvar(T t);

    void atualizar(T t, String[] valores);

    void deletar(T t);
}
