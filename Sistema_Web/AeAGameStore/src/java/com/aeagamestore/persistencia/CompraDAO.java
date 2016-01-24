/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Compra;
import com.aeagamestore.repositorios.CompraRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author arley
 */
@Singleton
public class CompraDAO extends DAOGenerico<Compra> implements CompraRepositorio {

    public CompraDAO() {
        super(Compra.class);
    }

    @Override
    public List<Compra> Buscar(Compra filtro) {
        if(filtro != null){
            return this.Like("data", filtro.getData().toString())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Compra obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
