/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.FormaDePagamento;
import com.aeagamestore.entidade.repositorios.FormaDePagamentoRepositorio;
import java.util.List;

/**
 *
 * @author xulipaxulepa
 */
public class FormaDePagamentoDAO extends DAOGenerico<FormaDePagamento> implements FormaDePagamentoRepositorio {
    
    public FormaDePagamentoDAO(){
        super(FormaDePagamento.class);
    }
    
    @Override
    public List<FormaDePagamento> Buscar(FormaDePagamento filtro) {
        if(filtro != null){
            return this.Like("tipo", filtro.getTipo())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }
}
