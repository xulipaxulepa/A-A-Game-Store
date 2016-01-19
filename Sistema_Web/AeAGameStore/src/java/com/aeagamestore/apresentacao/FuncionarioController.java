/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Funcionario;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.repositorios.FuncionarioRepositorio;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arley
 */
@ManagedBean
@Named(value = "funcionarioController")
@SessionScoped
public class FuncionarioController {

    /**
     * Creates a new instance of FuncionarioController
     */
    @EJB
    FuncionarioRepositorio dao;
    
    private Funcionario entidade;
    
    Cargo cargo = new Cargo();

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    Funcionario filtro;
    
    protected Telefone telefone;
    
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

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
    public FuncionarioController() {
        filtro = new Funcionario();
        telefone = new Telefone();
        entidade = new Funcionario();
    }
    
    public void limparCampos(){
        this.entidade = new Funcionario();
        this.telefone = new Telefone();
    }
    
    public void salvar(){
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
        filtro = new Funcionario();
    }
    
    public List<Funcionario> getListagem(){
        return dao.Buscar(null);
    }
    
    public Funcionario getFiltro() {
        return filtro;
    }

    public void setFiltro(Funcionario filtro) {
        this.filtro = filtro;
    }

    public Funcionario getEntidade() {
        return entidade;
    }

    public void setEntidade(Funcionario entidade) {
        this.entidade = entidade;
    }
}
