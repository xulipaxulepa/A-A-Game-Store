/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Genero;
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
@FacesConverter("generoConverter")
public class GeneroConverter implements Converter {

    /**
     * Creates a new instance of CargoConverte
     */
    public GeneroConverter() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            Genero genero = (Genero) uic.getAttributes().get(value);
            return genero;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Genero) {
            Genero entity= (Genero) object;
            if (entity != null && entity instanceof Genero && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
