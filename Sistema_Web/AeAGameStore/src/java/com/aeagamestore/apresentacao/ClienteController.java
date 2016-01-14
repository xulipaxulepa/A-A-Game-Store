/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Pessoa;
import com.aeagamestore.repositorios.ClienteRepositorio;
import com.aeagamestore.repositorios.PessoaRepositorio;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Scope;

/**
 *
 * @author arley
 */

@Named(value = "ClienteController")
@Dependent
public class ClienteController {

    /**
     * Creates a new instance of ClienteController
     */
    public ClienteController() {
    }
    
    @EJB
    ClienteRepositorio dao;
    
    private Cliente filtro;
    
    private Cliente entidade;
    
    /**
     * Creates a new instance of PessoaController
     */
    
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
    

    
    public void salvar(){
        if(dao.Salvar(entidade))
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
        else
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
    }
    
    public String apagar(){
        if(dao.Apagar(entidade))
            return "PessoaListagem.xhtml";
        else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }
    
    public void filtrar(){
        
    }
    
    public void limpar(){
        filtro = new Cliente();
    }
    
    public List<Pessoa> getListagem(){
        return dao.Buscar(filtro);
    }

    public Pessoa getFiltro() {
        return filtro;
    }

    public void setFiltro(Cliente filtro) {
        this.filtro = filtro;
    }

    public Pessoa getEntidade() {
        return entidade;
    }

    public void setEntidade(Cliente entidade) {
        this.entidade = entidade;
    }
    
}
