/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.Venda;
import com.aeagamestore.repositorios.VendaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author xulipaxulepa
 */
@Singleton
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio {

    public VendaDAO() {
        super(Venda.class);
    }

    @Override
    public List<Venda> Buscar(Venda filtro) {

        if (filtro != null) {
            if (filtro.cliente != null) {
                return this.IgualA("CLIENTE_ID", filtro.getCliente().getId()).Buscar();
            } else {
                return this.IgualA("id", filtro.getId())
                        .OrderBy("data", "ASC").Buscar();
            }
        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Venda obj
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
