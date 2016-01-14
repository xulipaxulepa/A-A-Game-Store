/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.Pessoa;
import com.aeagamestore.entidade.Produto;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;




/**
 *
 * @author Arley
 */
@Singleton
public class PessoaDAO extends DAOGenerico<Pessoa> {

    public PessoaDAO() {
        super(Pessoa.class);
    }

    @Override
    public List<Pessoa> Buscar(Pessoa filtro) {
        if(filtro != null){
            return this.Like("nome", filtro.getNome())
                   .IgualA("id", filtro.getId())
                   .OrderBy("nome", "ASC").Buscar();
            
        }
        return this.Buscar();
    }
    


 
    public Fornecedor Abrir(String cpf) {
        Query consulta = manager.createQuery("select p from Pessoa p where p.cpf =:p0");
        return (Fornecedor) consulta
                .setParameter("p0", cpf)
                .getSingleResult();
    }

    @Override
    public boolean Atualizar(Pessoa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
