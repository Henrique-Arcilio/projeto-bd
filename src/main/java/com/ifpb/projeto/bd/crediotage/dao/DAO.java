package com.ifpb.projeto.bd.crediotage.dao;

import java.util.List;

public interface DAO <T> {
    List<T> listar();

    void salvar(T t);

    void atualizar(T t, String[] valores);

    void deletar(T t);
}
