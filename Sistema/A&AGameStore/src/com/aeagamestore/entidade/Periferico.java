/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author grupoandroid
 */

@Entity
@Table(name = "perifericos")
public class Periferico extends Produto implements Serializable {
    
    @ManyToMany
    private Console console;

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }
    
    
    
}
