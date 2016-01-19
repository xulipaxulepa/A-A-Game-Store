/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
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
@FacesConverter("cargoConverter")
public class CargoConverter implements Converter {

    /**
     * Creates a new instance of CargoConverte
     */
    public CargoConverter() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            Cargo cargo = (Cargo) uic.getAttributes().get(value);
            return cargo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Cargo) {
            Cargo entity= (Cargo) object;
            if (entity != null && entity instanceof Cargo && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
