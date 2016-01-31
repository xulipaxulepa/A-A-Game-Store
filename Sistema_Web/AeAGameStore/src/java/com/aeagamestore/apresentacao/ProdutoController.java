/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Console;
import com.aeagamestore.entidade.FotoProduto;
import com.aeagamestore.entidade.Jogo;
import com.aeagamestore.entidade.Periferico;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author arley
 */
@Named(value = "ProdutoController")
@SessionScoped
public class ProdutoController implements Serializable {

    private Produto produtoCompra, filtro, entidade;

    public Produto getEntidade() {
        return entidade;
    }

    public void setEntidade(Produto entidade) {
        this.entidade = entidade;
    }

    @EJB
    ProdutoRepositorio dao;

    public ProdutoController() {
        filtro = new Produto();
        entidade = new Produto();
    }

    public List<Produto> getListagem() {
        return dao.Buscar(filtro);
    }

    public List<Produto> getAutoComplete(String nome) {
        Produto produto = new Produto();
        produto.setNome(nome);
        return dao.Buscar(produto);
    }

    public boolean renderize(String tipo) {
        if (tipo.equals("Jogo")) {
            if (entidade instanceof Jogo) {
                return true;
            } else {
                return false;
            }
        } else if (tipo.equals("Console")) {
            if (entidade instanceof Console) {
                return true;
            } else {
                return false;
            }
        } else if (tipo.equals("Periferico")) {
            if (entidade instanceof Periferico) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Produto getProdutoCompra() {
        return produtoCompra;
    }

    public void setProdutoCompra(Produto produtoCompra) {
        this.produtoCompra = produtoCompra;
    }

    public void limpar() {
        this.filtro = new Produto();
    }

    public void filtrar() {
    }

    public Produto getFiltro() {
        return filtro;
    }

    public void setFiltro(Produto filtro) {
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

    public String limparEntidadeVoltar() {
        this.entidade = new Produto();
        return "ProdutoListagem.xhtml";
    }

    public void limparEntidade() {
        this.entidade = new Produto();
    }

    public void salvarDados() {
        if (dao.Salvar(this.entidade)) {
            MensagemSucesso("Sucesso", "Registro salvo com sucesso!");
            this.limparEntidade();
        } else {
            MensagemErro("Falha!","Erro ao salvar o registro. Contacte o administrador do sistema!");
        }
    }

    public void salvar() {
        try {
            if (this.entidade == null) {
                throw new Exception("Nenhum produto selecionado");
            }
            if (dao.Salvar(this.entidade)) {
                MensagemSucesso("Sucesso", "Registro salvo com sucesso!");
                this.limparEntidade();
            } else {
                throw new Exception("Erro ao salvar o registro. Contacte o administrador do sistema!");
            }
        } catch (Exception ex) {
            this.MensagemErro("Erro!", ex.getMessage());
        }

    }
}
