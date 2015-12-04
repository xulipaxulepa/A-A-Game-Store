/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

/**
 *
 * @author Arley
 */
public interface FornecedorRepositorio extends Repositorio<Fornecedor>{
    public Fornecedor Abrir(String cnpj);
}
