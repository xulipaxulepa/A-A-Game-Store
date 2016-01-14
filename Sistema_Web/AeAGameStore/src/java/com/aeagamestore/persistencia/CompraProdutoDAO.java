/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.CompraProduto;
import com.aeagamestore.repositorios.CompraProdutoRepositorio;
import java.util.List;


/**
 *
 * @author xulipaxulepa
 */
public class CompraProdutoDAO extends DAOGenerico<CompraProduto> implements CompraProdutoRepositorio{
    
    public CompraProdutoDAO(){
        super(CompraProduto.class);
    }
    
    @Override
    public List<CompraProduto> Buscar(CompraProduto filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getFornecedor().getNome())
                   .Like("nome", filtro.getProduto().getNome())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(CompraProduto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
