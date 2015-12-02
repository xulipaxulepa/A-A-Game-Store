/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aeagamestore.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Xulipaxulepa
 */
@Entity
@Table(name = "vendas")
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    public Pessoa cliente;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal valorTotal;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "venda")
    private List<ItemVenda> itens;

    public Venda() {
        this.itens = new LinkedList<>();
        this.valorTotal = new BigDecimal("0.00");
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
    
    public void add(ItemVenda i){
        this.itens.add(i);
        this.valorTotal.add(i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())));
    }
    
    public void remove(ItemVenda i){
        this.itens.remove(i);
        this.valorTotal.subtract(i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())));
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.MeuPrimeiroJPA.Entidades.Venda[ id=" + id + " ]";
    }
    
}
