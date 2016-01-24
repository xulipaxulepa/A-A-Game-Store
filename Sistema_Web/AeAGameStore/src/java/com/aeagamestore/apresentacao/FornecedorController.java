/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.Funcionario;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.repositorios.FornecedorRepositorio;
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
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    /**
     * Creates a new instance of FornecedorController
     */
    Fornecedor entidade, filtro;
    Telefone telefone;

    @EJB
    FornecedorRepositorio dao;

    public List<Fornecedor> getListagem() {
        return dao.Buscar(filtro);
    }

    public Fornecedor getEntidade() {
        return entidade;
    }

    public void setEntidade(Fornecedor entidade) {
        this.entidade = entidade;
    }

    public Fornecedor getFiltro() {
        return filtro;
    }

    public void setFiltro(Fornecedor filtro) {
        this.filtro = filtro;
    }

    public void limparCampos() {
        this.entidade = new Fornecedor();
        this.telefone = new Telefone();
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public void salvar() {
        this.entidade.addTelefone(telefone);
        if (dao.Salvar(entidade)) {
            this.limparCampos();
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
        } else {
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
        }
    }

    public String apagar() {
        if (dao.Apagar(entidade)) {
            return "ClienteListagem.xhtml";
        } else {
            MensagemErro("Falha!", "Erro ao apagar o registro. Contacte o administrador do sistema!");
            return "";
        }
    }

    public void filtrar() {

    }

    public void limpar() {
        filtro = new Fornecedor();
    }
    
    public String limparEntidade(){
        entidade = new Fornecedor();
        return "FornecedorListagem.xhtml";
    }

    public FornecedorController() {
        this.entidade = new Fornecedor();
        this.filtro = new Fornecedor();
        this.telefone = new Telefone();
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
    
    public List<Fornecedor> getAutoComplete(String nome) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        List<Fornecedor> fornecedores = dao.Buscar(fornecedor);
        return fornecedores;
    }

}
