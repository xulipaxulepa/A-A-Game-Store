/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.persistencia.FornecedorDAO;
import com.aeagamestore.repositorios.FornecedorRepositorio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Arley
 */
@FacesConverter("produtoConverter")
@Named("produtoConverter")
@Singleton
public class ProdutoConverter implements Converter {

    /**
     * Creates a new instance of CargoConverte
     */

    public ProdutoConverter() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            Produto produto = (Produto) uic.getAttributes().get(value);
            return produto;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Produto) {
            Produto entity= (Produto) object;
            if (entity != null && entity instanceof Produto && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }   
}
