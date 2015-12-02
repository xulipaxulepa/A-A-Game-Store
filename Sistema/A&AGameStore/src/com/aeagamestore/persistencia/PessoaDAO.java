/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Pessoa;
import br.edu.ifnmg.MeuPrimeiroJPA.Entidades.PessoaRepositorio;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
public class PessoaDAO extends DAOGenerico<Pessoa> implements PessoaRepositorio {

    @Override
    public Pessoa Abrir(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PessoaDAO() {
        super(Pessoa.class);
    }

    @Override
    public List<Pessoa> Buscar(Pessoa filtro) {
        Query query = manager.createQuery("Select p from Pessoa p");
        return query.getResultList();
    }

}
