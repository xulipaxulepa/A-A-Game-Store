/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.FotoProduto;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author arley
 */
@Named(value = "ProdutoController")
@SessionScoped
public class ProdutoController implements Serializable {

    private Produto produtoCompra, filtro;

    @EJB
    ProdutoRepositorio dao;

    public ProdutoController() {
        filtro = new Produto();
    }

    public List<Produto> getListagem() {
        return dao.Buscar(filtro);
    }

    public List<Produto> getAutoComplete(String nome) {
        Produto produto = new Produto();
        produto.setNome(nome);
        return dao.Buscar(produto);
    }

    public Produto getProdutoCompra() {
        return produtoCompra;
    }

    public void setProdutoCompra(Produto produtoCompra) {
        this.produtoCompra = produtoCompra;
    }

    public void limpar(){
        this.filtro = new Produto();
    }
    
    public void filtrar(){
    }
        
    
    public Produto getFiltro() {
        return filtro;
    }

    public void setFiltro(Produto filtro) {
        this.filtro = filtro;
    }

    public void salvar(Produto produto) {
        dao.Salvar(produto);
    }
}
