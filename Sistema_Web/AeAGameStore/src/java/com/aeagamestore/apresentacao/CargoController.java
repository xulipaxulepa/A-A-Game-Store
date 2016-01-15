/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.repositorios.CargoRepositorio;
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
@Named(value = "CargoController")
@SessionScoped
public class CargoController implements Serializable {

    Cargo entidade;
    Cargo filtro;
    
    @EJB
    CargoRepositorio dao;

    public Cargo getEntidade() {
        return entidade;
    }

    public void setEntidade(Cargo entidade) {
        this.entidade = entidade;
    }

    public Cargo getFiltro() {
        return filtro;
    }

    public void setFiltro(Cargo filtro) {
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
        this.entidade = new Cargo();
    }
    
    public void salvar(){
        //entidade.addTelefone(telefone);
        if(dao.Salvar(entidade)){
            this.limparCampos();
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
        }else
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
    }
    
    public String apagar(){
        if(dao.Apagar(entidade))
            return "ClienteListagem.xhtml";
        else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }
    
    public void filtrar(){
        
    }
    
    public void limpar(){
        filtro = new Cargo();
    }
    
    public List<Cargo> getListagem(){
        return dao.Buscar(filtro);
    }

    public CargoController() {
        entidade = new Cargo();
        filtro = new Cargo();
    }
    
}
