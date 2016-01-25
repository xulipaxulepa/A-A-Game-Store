/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Console;
import com.aeagamestore.entidade.Genero;
import com.aeagamestore.entidade.ItemVenda;
import com.aeagamestore.entidade.Jogo;
import com.aeagamestore.entidade.Periferico;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.entidade.Venda;
import com.aeagamestore.repositorios.ClienteRepositorio;
import com.aeagamestore.repositorios.VendaRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author arley
 */
@Named(value = "vendaProduto")
@SessionScoped
@ViewScoped
public class VendaProduto implements Serializable {

    /**
     * Creates a new instance of VendaProduto
     */
    private Venda entidade;
    private Produto produto;
    private Cliente cliente;
    private String email, senha;
    private boolean skip;

    @EJB
    ClienteRepositorio daoCliente;

    @EJB
    VendaRepositorio dao;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VendaProduto() {
        this.entidade = new Venda();
    }

    public Venda getEntidade() {
        return entidade;
    }

    public void setEntidade(Venda entidade) {
        this.entidade = entidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean rederize(String descricao) {
        if (descricao.equals("jogo")) {
            if (produto instanceof Jogo) {
                return true;
            } else {
                return false;
            }
        }

        if (descricao.equals("console")) {
            if (produto instanceof Console) {
                return true;
            } else {
                return false;
            }
        }

        if (descricao.equals("periferico")) {
            if (produto instanceof Periferico) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String onFlowProcess(FlowEvent event) {
        if (this.cliente != null) {
            if (skip) {
                skip = false;   //reset in case user goes back
                return event.getNewStep();
            } else {
                return event.getNewStep();
            }
        } else {
            MensagemErro("Impossível avançar", "Realize o login ou se registre-se!");
            return event.getOldStep();
        }

    }

    public boolean usuarioLogado(String descricao) {
        if (descricao.equals("formLogin")) {
            if (cliente != null) {
                return false;
            } else {
                return true;
            }
        } else if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        this.cliente = null;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void autenticar() {
        try {
            Cliente c = null;
            c = daoCliente.autentica(email, senha);
            this.cliente = c;
            this.skip = true;
            MensagemSucesso("Sucesso!", "Login realizado com sucesso!");
        } catch (Exception ex) {
            MensagemErro("Falha no login!", ex.getMessage());
            this.skip = false;
        }
    }
    
    public void limparCampos(){
        
    }
    
    public void salvar(){
        this.entidade.cliente = this.cliente;
        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(1);
        item.getValor().add(produto.getValor().multiply(new BigDecimal(1)));
        this.entidade.add(item);
        this.entidade.setData(new Date());
        if(dao.Salvar(entidade)){
            this.limparCampos();
            MensagemSucesso("Sucesso!", "Compra realizada com sucesso!");
        }else
            MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
    }
}
