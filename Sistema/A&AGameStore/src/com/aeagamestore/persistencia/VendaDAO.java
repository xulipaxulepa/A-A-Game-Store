/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aeagamestore.persistencia;

import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Produto;
import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Venda;
import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.VendaRepositorio;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio {

    public VendaDAO() {
        super(Venda.class);
    }
    
    @Override
    public List<Venda> Buscar(Venda filtro) {
        Query query = manager.createQuery("Select p from Venda p");
        return query.getResultList();
    }
    
}
