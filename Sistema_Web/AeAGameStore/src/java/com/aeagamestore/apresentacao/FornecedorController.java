/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.repositorios.FornecedorRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author arley
 */
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    /**
     * Creates a new instance of FornecedorController
     */
    
    @EJB
    FornecedorRepositorio dao;
    
    public List<Fornecedor> getListagem(){
        return dao.Buscar(null);
    }
    
    public FornecedorController() {
    }
    
    
    
}
