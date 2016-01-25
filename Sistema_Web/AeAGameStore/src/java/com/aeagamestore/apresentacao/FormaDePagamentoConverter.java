/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.FormaDePagamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Arley
 */
@FacesConverter("formaDePagamentoConverter")
public class FormaDePagamentoConverter implements Converter {

    /**
     * Creates a new instance of CargoConverte
     */
    public FormaDePagamentoConverter() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            FormaDePagamento forma = (FormaDePagamento) uic.getAttributes().get(value);
            return forma;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof FormaDePagamento) {
            FormaDePagamento entity= (FormaDePagamento) object;
            if (entity != null && entity instanceof FormaDePagamento && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
