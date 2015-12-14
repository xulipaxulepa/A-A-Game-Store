/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Produto;
import com.aeagamestore.entidade.repositorios.ProdutoRepositorio;
import java.util.List;

/**
 *
 * @author arley
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio{

    public ProdutoDAO() {
        super(Produto.class);
    }

    @Override
    public List<Produto> Buscar(Produto filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .Like("nome", filtro.getDescricao())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }
     
}
