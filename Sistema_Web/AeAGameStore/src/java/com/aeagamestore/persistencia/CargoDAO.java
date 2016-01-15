/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.repositorios.CargoRepositorio;
import java.util.List;

/**
 *
 * @author arley
 */
public class CargoDAO extends DAOGenerico<Cargo> implements CargoRepositorio{

    public CargoDAO() {
        super(Cargo.class);
    }

    @Override
    public List<Cargo> Buscar(Cargo filtro) {
         if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .IgualA("Observacao", filtro.getObservacao())
                    .Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Cargo obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
