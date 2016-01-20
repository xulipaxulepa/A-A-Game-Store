/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Genero;
import com.aeagamestore.repositorios.GeneroRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author arley
 */
@ManagedBean
@Named(value = "generoController")
@SessionScoped
public class GeneroController implements Serializable {

    /**
     * Creates a new instance of GeneroController
     */
    Genero entidade, filtro;

    @EJB
    GeneroRepositorio dao;

    public GeneroController() {
        this.entidade = new Genero();
        this.filtro = new Genero();
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
        this.entidade = new Genero();
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            this.limparCampos();
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
        } else {
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
        }
    }

    public String apagar() {
        if (dao.Apagar(entidade)) {
            limparCampos();
            return "CargoListagem.xhtml";
        } else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }

    public void filtrar() {

    }

    public Genero getEntidade() {
        return entidade;
    }

    public void setEntidade(Genero entidade) {
        this.entidade = entidade;
    }

    public Genero getFiltro() {
        return filtro;
    }

    public void setFiltro(Genero filtro) {
        this.filtro = filtro;
    }

    public void limpar() {
        filtro = new Genero();
    }

    public List<Genero> getListagem() {
        return dao.Buscar(filtro);
    }

}
