/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Compra;
import com.aeagamestore.entidade.Console;
import com.aeagamestore.entidade.Genero;
import com.aeagamestore.entidade.ItemCompra_;
import com.aeagamestore.entidade.ItemVenda;
import com.aeagamestore.entidade.Jogo;
import com.aeagamestore.entidade.Periferico;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.entidade.Venda;
import com.aeagamestore.repositorios.ClienteRepositorio;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import com.aeagamestore.repositorios.VendaRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
@Named(value = "vendaController")
@SessionScoped
@ViewScoped
public class VendaController implements Serializable {

    /**
     * Creates a new instance of VendaController
     */
    private Venda entidade, filtro;
    private Cliente cliente;
    private String email, senha;
    private boolean skip;
    private ItemVenda i;
    private Produto produto;
    @EJB
    ClienteRepositorio daoCliente;

    @EJB
    VendaRepositorio dao;

    @EJB
    ProdutoRepositorio daoProduto;

    public ItemVenda getI() {
        return i;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setI(ItemVenda i) {
        this.i = i;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VendaController() {
        this.entidade = new Venda();
        this.i = new ItemVenda();
        this.filtro = new Venda();
    }

    public Venda getEntidade() {
        return entidade;
    }

    public void setEntidade(Venda entidade) {
        this.entidade = entidade;
    }

    public void atualizaEstoque() {
        for (ItemVenda item : this.entidade.getItens()) {
            item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantidade());
            daoProduto.Salvar(item.getProduto());
        }
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

    public void excluirItem() {
        this.entidade.remove(i);
        MensagemSucesso("Suceso!", "Item foi removido com sucesso!");
        limparItem();
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

    public void limparCampos() {
        this.produto = new Produto();
        this.entidade = new Venda();
        this.email = "";
        this.senha = "";
        this.limparItem();
    }

    public void limparItem() {
        this.i = new ItemVenda();
    }

    public void addItem() {
        this.entidade.add(i);
        this.limparItem();
    }

    public void addClienteVenda() {
        this.entidade.setCliente(cliente);
    }

    public String salvar() {
        if (!this.entidade.getItens().isEmpty()) {
            this.addClienteVenda();
            if (dao.Salvar(entidade)) {
                this.atualizaEstoque();
                this.limparCampos();
                MensagemSucesso("Sucesso!", "Compra realizada com sucesso!");
                return "index.xhtml";
            } else {
                MensagemErro("Falha!", "Erro ao salvar o registro. Contacte o administrador do sistema!");
                return "";
            }
        } else {
            MensagemErro("Erro!", "Não há nenhum item!");
            return "";
        }
    }

    public void addProdutoAoItemVenda() {
        try {
            for (ItemVenda item : this.entidade.getItens()) {
                if (this.i.getProduto().equals(item.getProduto())) {
                    throw new RuntimeException("Produto iguais, foram atualizados");
                }
            }
            this.i.setProduto(this.produto);
            this.i.setValor(i.getProduto().getValor());
            this.entidade.add(i);
            this.limparItem();
        } catch (RuntimeException e) {
            MensagemSucesso("Produtos iguais", "Foi mantido o anterior");
        }
    }

    public void addProdutoAoCarrinho() {
        this.addProdutoAoItemVenda();
        this.MensagemSucesso("Sucesso!", "Item adicionado com sucesso!");
    }

    public String addProdutoIrParaConcretirzarVenda() {
        this.addProdutoAoItemVenda();
        return "EtapasDaCompra.xhtml";
    }

    public List<Venda> getListagem() {
        return dao.Buscar(null);
    }
    
    public void limpar(){
        this.filtro = new Venda();
    }

    public Venda getFiltro() {
        return filtro;
    }

    public void setFiltro(Venda filtro) {
        this.filtro = filtro;
    }
    
    public void filtrar(){
    }
}
