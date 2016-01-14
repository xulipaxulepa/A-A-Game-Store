/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.repositorios.ClienteRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author arley
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    /**
     * Creates a new instance of ClienteController
     */
    
    @EJB
    ClienteRepositorio dao;
    
    Cliente cliente;
    
    public ClienteController() {
        cliente = new Cliente();
    }
    
    
    public List<Cliente> getListagem(){
        return dao.Buscar(cliente);
    }
    
}