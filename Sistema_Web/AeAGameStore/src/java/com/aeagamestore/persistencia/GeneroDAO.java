/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Genero;
import com.aeagamestore.repositorios.GeneroRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author arley
 */
@Singleton
public class GeneroDAO extends DAOGenerico<Genero> implements GeneroRepositorio{

    public GeneroDAO() {
        super(Genero.class);
    }

    @Override
    public List<Genero> Buscar(Genero filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .IgualA("Observacao", filtro.getDescricao())
                    .Buscar();   
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
