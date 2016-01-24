/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Compra;
import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.ItemCompra;
import com.aeagamestore.repositorios.CompraRepositorio;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author arley
 */
@Named(value = "compraController")
@SessionScoped
public class CompraController implements Serializable {

    Compra entidade, filtro;

    ItemCompra i;

    @EJB
    CompraRepositorio dao;
    
    @EJB
    ProdutoRepositorio daoProduto;

    public CompraController() {
        this.entidade = new Compra();
        this.filtro = new Compra();
        this.i = new ItemCompra();
    }

    public Compra getEntidade() {
        return entidade;
    }

    public void setEntidade(Compra entidade) {
        this.entidade = entidade;
    }

    public Compra getFiltro() {
        return filtro;
    }

    public void setFiltro(Compra filtro) {
        this.filtro = filtro;
    }

    protected void MensagemSucesso(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    protected void MensagemErro(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public void limparCampos() {
        this.entidade = new Compra();
    }

    public void onItemSelect(SelectEvent event) {
        this.entidade.setFornecedor((Fornecedor) event.getObject());
    }

    public void addProduto() {
        try {
            for (ItemCompra item : this.entidade.getItens()) {
                if (item.getProduto().equals(i.getProduto())) {
                    throw new RuntimeException();
                }
            }
            if (i.getProduto() != null && i.getQuantidade() > 0) {
                this.entidade.add(i);
                this.entidade.setValor((BigDecimal) this.entidade.getValor().add(i.getValorTotal()));
                this.limparItemCompra();
                MensagemSucesso("Sucesso!", "Produto adicionado com sucesso!");
            } else {
                MensagemErro("Erro!", "Preenchar os campos obrigatorios");
            }
        } catch (RuntimeException e) {
            limparItemCompra();
            MensagemErro("Erro!", "Este produto já esta na lista!");
        }
    }

    public void excluirItem() {
        this.entidade.remove(i);
        this.entidade.setValor(this.entidade.getValor().subtract(i.getValorTotal()));
        this.limparItemCompra();
    }

    public void editarItem() {
        this.entidade.setValor(this.entidade.getValor().subtract(i.getValorTotal()));
        this.entidade.remove(i);
    }

    public void limparItemCompra() {
        this.i = new ItemCompra();
    }
    
    public void atualizaEstoqueProduto(){
        for (ItemCompra i : this.entidade.getItens()) { 
            i.getProduto().setEstoque(i.getProduto().getEstoque() + i.getQuantidade());
            daoProduto.Salvar(i.getProduto());
        }
    }

    public void salvar() {
        
        if(this.entidade.getFornecedor() != null && this.entidade.getData() != null) {
            if (!this.entidade.getItens().isEmpty()) {
                if (dao.Salvar(entidade)) {
                    this.atualizaEstoqueProduto();
                    this.limparCampos();
                    this.limparItemCompra();
                    MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
                } else {
                    MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
                }
            } else {
                MensagemErro("Erro!", "Nenhum item foi adicinado a compra!");
            }
        }else{
            MensagemErro("Erro!", "Preencher campos obrigátorios");
        }

    }

    public String apagar() {
        if (dao.Apagar(entidade)) {
            limparCampos();
            return "CompraListagem.xhtml";
        } else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }

    public ItemCompra getI() {
        return i;
    }

    public void setI(ItemCompra i) {
        this.i = i;
    }

    public void filtrar() {

    }

    public void limpar() {
        filtro = new Compra();
    }

    public String limparEntidade() {
        entidade = new Compra();
        this.limparItemCompra();
        return "CompraListagem.xhtml";
    }

    public List<Compra> getListagem() {
        return dao.Buscar(null);
    }

}
