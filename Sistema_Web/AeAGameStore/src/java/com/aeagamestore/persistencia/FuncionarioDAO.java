/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Funcionario;
import com.aeagamestore.repositorios.FuncionarioRepositorio;
import java.util.List;


/**
 *
 * @author arley
 */
public class FuncionarioDAO extends DAOGenerico<Funcionario> implements FuncionarioRepositorio{

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> Buscar(Funcionario filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .IgualA("cpf", filtro.getCpf())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Funcionario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
