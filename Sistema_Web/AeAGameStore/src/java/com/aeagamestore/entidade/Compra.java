/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Arley
 */
@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Fornecedor fornecedor;
    
    @Column(precision = 6, scale = 2)
    private BigDecimal valor;
    

    @Temporal(TemporalType.DATE)
    private Date data;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCompra> itens;

    public Compra(){
        this.itens = new LinkedList<>();
        this.valor = new BigDecimal("0.00");
        this.fornecedor = new Fornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    public void add(ItemCompra i){
        this.itens.add(i);
        //this.valor.add((BigDecimal)i.getValorTotal());
    }
    
    public void remove(ItemCompra i){
        this.itens.remove(i);
        this.valor.subtract(i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())));
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public String getDataFormatada(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        return sdf.format(data);
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", fornecedor=" + fornecedor + ", valor=" + valor + ", data=" + data + ", itens=" + itens + '}';
    }
   
}
