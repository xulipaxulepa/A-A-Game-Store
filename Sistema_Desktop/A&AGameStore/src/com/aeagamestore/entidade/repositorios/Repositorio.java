/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade.repositorios;

import java.util.List;

/**
 *
 * @author Arley
 */
public interface Repositorio<T> {
    public boolean Salvar(T obj);
    public T Abrir(Long id);
    public boolean Apagar(T obj);
    public List<T> Buscar(T filtro);
    public boolean Atualizar(T obj);
}
