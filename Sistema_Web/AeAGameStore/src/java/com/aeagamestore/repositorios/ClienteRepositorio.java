/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.repositorios;

import com.aeagamestore.entidade.Cliente;

/**
 *
 * @author arley
 */

public interface ClienteRepositorio extends Repositorio<Cliente>{
    public Cliente autentica(String email, String senha) throws Exception;
}
