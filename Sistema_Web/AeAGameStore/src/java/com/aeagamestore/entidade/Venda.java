/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
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
 * @author arley
 */
@Entity
@Table(name = "vendas")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    public Cliente cliente;

    @ManyToOne
    public Funcionario funcionario;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal valor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<ItemVenda> itens;

    @Temporal(TemporalType.DATE)
    private Date data;

    @ManyToOne
    private FormaDePagamento formaDePagamento;

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Venda() {
        this.itens = new LinkedList<>();
        this.valor = new BigDecimal(0.00);
        this.data = new Date();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorTotal() {
        return valor;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valor = valorTotal;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void add(ItemVenda i) {
        this.itens.add(i);
        double valorVenda = this.valor.doubleValue();
        valorVenda = valorVenda + i.getValorTotal().doubleValue();
        BigDecimal valorAtualizado = new BigDecimal(valorVenda);
        this.setValor(valorAtualizado);
    }

    public void remove(ItemVenda i) {
        this.itens.remove(i);
        double valorVenda = this.valor.doubleValue();
        valorVenda = valorVenda - i.getValorTotal().doubleValue();
        BigDecimal valorAtualizado = new BigDecimal(valorVenda);
        this.setValor(valorAtualizado);
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

    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
