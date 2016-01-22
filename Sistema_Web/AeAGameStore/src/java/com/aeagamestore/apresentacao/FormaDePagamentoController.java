/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.FormaDePagamento;
import com.aeagamestore.repositorios.FormaDePagamentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author arley
 */
@Named(value = "formaDePagamentoController")
@SessionScoped
public class FormaDePagamentoController implements Serializable {

    
    FormaDePagamento entidade, filtro;
    
    @EJB
    FormaDePagamentoRepositorio dao;
    
    public FormaDePagamentoController() {
        this.entidade = new FormaDePagamento();
        this.filtro = new FormaDePagamento();
    }

    public FormaDePagamento getEntidade() {
        return entidade;
    }

    public void setEntidade(FormaDePagamento entidade) {
        this.entidade = entidade;
    }

    public FormaDePagamento getFiltro() {
        return filtro;
    }

    public void setFiltro(FormaDePagamento filtro) {
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
 
    public void limparCampos(){
        this.entidade = new FormaDePagamento();
    }
    
    public void salvar(){
        if(dao.Salvar(entidade)){
            this.limparCampos();
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
        }else
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
    }
    
    public String apagar(){
        if(dao.Apagar(entidade)){
            limparCampos();
            return "CargoListagem.xhtml";
        }
        else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }
    
    public void filtrar(){
        
    }
    
    public void limpar(){
        filtro = new FormaDePagamento();
    }
    
    public String limparEntidade(){
        entidade = new FormaDePagamento();
        return "FormaDePagamentoListagem.xhtml";
    }
    
    public List<FormaDePagamento> getListagem(){
        return dao.Buscar(filtro);
    }
    
}
