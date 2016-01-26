/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Fabricante;
import com.aeagamestore.repositorios.FabricanteRepositorio;
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
@Named(value = "fabricanteController")
@SessionScoped
public class FabricanteController implements Serializable {

    @EJB
    FabricanteRepositorio dao;
    
    Fabricante entidade, filtro;

    public Fabricante getEntidade() {
        return entidade;
    }

    public void setEntidade(Fabricante entidade) {
        this.entidade = entidade;
    }

    public Fabricante getFiltro() {
        return filtro;
    }

    public void setFiltro(Fabricante filtro) {
        this.filtro = filtro;
    }
    
    public FabricanteController() {
        this.filtro = new Fabricante();
        this.entidade = new Fabricante();
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
        this.entidade = new Fabricante();
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
            MensagemSucesso("Sucesso!", "Registro deletado com sucesso!");
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
        filtro = new Fabricante();
    }
    
    public String limparEntidade(){
        entidade = new Fabricante();
        return "FabricanteListagem.xhtml";
    }
    
    public List<Fabricante> getListagem(){
        return dao.Buscar(filtro);
    }
}
