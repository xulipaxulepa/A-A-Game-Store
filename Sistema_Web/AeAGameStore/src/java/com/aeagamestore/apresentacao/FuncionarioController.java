/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Funcionario;
import com.aeagamestore.repositorios.FuncionarioRepositorio;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author arley
 */
@Named(value = "funcionarioController")
@Dependent
public class FuncionarioController {

    /**
     * Creates a new instance of FuncionarioController
     */
    @EJB
    FuncionarioRepositorio dao;
    Funcionario filtro;
    
    public FuncionarioController() {
        filtro = new Funcionario();
    }
    
    
    public List<Funcionario> getListagem(){
        return dao.Buscar(null);
    }
}
