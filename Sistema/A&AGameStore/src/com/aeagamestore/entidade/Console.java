/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author grupoandroid
 */

@Entity
@Table(name = "consoles")
public class Console extends Produto implements Serializable {
    
    @Column(nullable = false, length = 500)
    private String versão;

    public String getVersão() {
        return versão;
    }

    public void setVersão(String versão) {
        this.versão = versão;
    }
    
    
}
