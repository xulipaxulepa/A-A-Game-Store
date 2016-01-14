/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.repositorios.FornecedorRepositorio;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;



/**
 *
 * @author Arley
 */
@Singleton
public class FornecedorDAO extends DAOGenerico<Fornecedor> implements FornecedorRepositorio{

    public FornecedorDAO() {
        super(Fornecedor.class);
    }

    @Override
    public List<Fornecedor> Buscar(Fornecedor filtro) {
        Query query = manager.createQuery("Select f from Fornecedor f");
        return query.getResultList();
    }

    @Override
    public Fornecedor Abrir(String cnpj) {
        Query consulta = manager.createQuery("select f from Fornecedor f where p.cpf =:p0");
        return (Fornecedor) consulta
                .setParameter("p0", cnpj)
                .getSingleResult();
    }

    @Override
    public boolean Atualizar(Fornecedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
