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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author grupoandroid
 */
@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING, length = 32)
@DiscriminatorValue("produto")
public abstract class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 500)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    private String DTYPE;

    @Column(nullable = false)
    private int estoque;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal valor;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<FotoProduto> fotos;

    public List<FotoProduto> getFotos() {
        return fotos;
    }
    
    public void addFoto(FotoProduto foto){
        this.fotos.add(foto);
    }

    public void setFotos(List<FotoProduto> fotos) {
        this.fotos = fotos;
    }

    public Produto() {
        this.valor = new BigDecimal("0.00");
        this.fotos = new LinkedList<>();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
