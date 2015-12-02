/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aeagamestore.persistencia;

import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Pessoa;
import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Produto;
import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.ProdutoRepositorio;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio{

    public ProdutoDAO() {
        super(Produto.class);
    }
    
    @Override
    public List<Produto> Buscar(Produto filtro) {
        Query query = manager.createQuery("Select p from Produto p");
        return query.getResultList();
    }
    
}
