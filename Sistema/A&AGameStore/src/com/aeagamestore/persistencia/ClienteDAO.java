/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.FornecedorRepositorio;
import com.aeagamestore.entidade.Pessoa;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Arley
 */
public class ClienteDAO extends DAOGenerico<Cliente> {

    public ClienteDAO() {
        super(Fornecedor.class);
    }

    public List<Fornecedor> Buscar(Fornecedor filtro) {
        Query query = manager.createQuery("Select p from Cliente p");
        return query.getResultList();
    }

 
    public Fornecedor Abrir(String cnpj) {
        Query consulta = manager.createQuery("select p from Cliente p where p.cpf =:p0");
        return (Fornecedor) consulta
                .setParameter("p0", cnpj)
                .getSingleResult();
    }

    @Override
    public List<Cliente> Buscar(Cliente filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
