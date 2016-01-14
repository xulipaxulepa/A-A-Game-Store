/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author arley
 */
@Named(value = "ProdutoController")
@SessionScoped
public class ProdutoController implements Serializable {
    @EJB
    ProdutoRepositorio dao;
    
    public ProdutoController() {
    }
    
    
    public List<Produto> getListagem(){
        return dao.Buscar(null);
    }
    
    
}
