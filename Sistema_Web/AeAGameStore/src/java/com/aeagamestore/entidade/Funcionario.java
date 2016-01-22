/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author grupoandroid
 */

@Entity
@Table(name = "funcionarios")
@DiscriminatorValue("funcionario")
public class Funcionario extends Pessoa implements Serializable{
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cargo cargo;
    
    
    @Column(nullable = false)
    private BigDecimal salario;
    
    public Funcionario(){
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
    
}
