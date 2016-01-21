/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Fabricante;
import com.aeagamestore.repositorios.FabricanteRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author arley
 */
@Singleton
public class FabricanteDAO extends DAOGenerico<Fabricante> implements FabricanteRepositorio{

    public FabricanteDAO() {
        super(Fabricante.class);
    }

    @Override
    public List<Fabricante> Buscar(Fabricante filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .IgualA("website", filtro.getWebsite())
                   .Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Fabricante obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
