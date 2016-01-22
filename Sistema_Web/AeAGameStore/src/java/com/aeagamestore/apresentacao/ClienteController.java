
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.repositorios.ClienteRepositorio;
import com.sun.xml.ws.api.security.trust.Claims;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "ClienteController")
@SessionScoped
public class ClienteController implements Serializable {
    
    @EJB
    ClienteRepositorio dao;
    
    private Cliente filtro;
    
    private Cliente entidade;

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
    
    public ClienteController() {
        filtro = new Cliente();
        telefone = new Telefone();
        entidade = new Cliente();
    }
    
    public void limparCampos(){
        this.entidade = new Cliente();
        this.telefone = new Telefone();
    }
    
    public String limparEntidade(){
        entidade = new Cliente();
        return "ClienteListagem.xhtml";
    }
    
    public String editar(){
        if(dao.Salvar(entidade)){
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
            return "ClienteListagem.xhtml";
        }else
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
        return "";
               
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
        filtro = new Cliente();
    }
    
    public List<Cliente> getListagem(){
        return dao.Buscar(filtro);
    }

    public Cliente getFiltro() {
        return filtro;
    }

    public void setFiltro(Cliente filtro) {
        this.filtro = filtro;
    }

    public Cliente getEntidade() {
        return entidade;
    }

    public void setEntidade(Cliente entidade) {
        this.entidade = entidade;
    }
}