/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ClienteRepositorio;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;



/**
 *
 * @author Arley
 */

@Singleton
public class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio {

    public ClienteDAO() {
        super(Cliente.class);
    }
 
    public Cliente Abrir(String cpf) {
        Query consulta = manager.createQuery("select p from Cliente p where p.cpf =:p0");
        return (Cliente) consulta
                .setParameter("p0", cpf)
                .getSingleResult();
    }
    
    @Override
    public List<Cliente> Buscar(Cliente filtro) {

        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .IgualA("cpf", filtro.getCpf())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
