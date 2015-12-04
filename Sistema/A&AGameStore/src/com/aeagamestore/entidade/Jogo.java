/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author grupoandroid
 */

@Entity
@Table(name = "Jogos")
public class Jogo extends Produto implements Serializable{
    
    @Temporal(TemporalType.DATE)
    private Date dataDeLançamento;
    
    @Column(nullable = false)
    private int ESRB;
    
    @Column(nullable = false, length = 500)
    private String midia;
    
    @ManyToOne
    private Genero genero;

    public Date getDataDeLançamento() {
        return dataDeLançamento;
    }

    public void setDataDeLançamento(Date dataDeLançamento) {
        this.dataDeLançamento = dataDeLançamento;
    }

    public int getESRB() {
        return ESRB;
    }

    public void setESRB(int ESRB) {
        this.ESRB = ESRB;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
    
}
