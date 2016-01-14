/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Venda;
import com.aeagamestore.repositorios.VendaRepositorio;
import java.util.List;



/**
 *
 * @author xulipaxulepa
 */
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio{
    
    public VendaDAO(){
        super(Venda.class);
    }
    
    @Override
    public List<Venda> Buscar(Venda filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getCliente().getNome())
                   .Like("nome", filtro.getFuncionario().getNome())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Venda obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}