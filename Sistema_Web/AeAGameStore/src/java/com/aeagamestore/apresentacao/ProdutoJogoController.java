/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.FotoProduto;
import com.aeagamestore.entidade.Jogo;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author arley
 */
@ManagedBean
@Named(value = "produtoJogoController")
@SessionScoped
public class ProdutoJogoController implements Serializable {

    @EJB
    ProdutoRepositorio dao;

    Jogo entidade, filtro;

    List<FileUploadEvent> fotos;

    public Jogo getEntidade() {
        return entidade;
    }

    public void setEntidade(Jogo entidade) {
        this.entidade = entidade;
    }

    public Jogo getFiltro() {
        return filtro;
    }

    public void setFiltro(Jogo filtro) {
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

    public void limparCampos() {
        this.entidade = new Jogo();
        this.filtro = new Jogo();
        this.fotos = new LinkedList<>();
    }

    public void salvar() {
        FotoProduto foto;
        try {
            for (FileUploadEvent imagem : fotos) {
                foto = dao.SalvarImagemDiretorio(imagem.getFile().getInputstream(), imagem.getFile().getFileName());
                this.entidade.addFoto(foto);
            }
            if (dao.Salvar(entidade)) {
                this.limparCampos();
                MensagemSucesso("Sucesso!", "Registro salvo com sucesso!");
            } else {
                throw new RuntimeException();
            }

        } catch (Exception ex) {
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

    public void limpar() {
        filtro = new Jogo();
    }

    public List<Produto> getListagem() {
        return dao.Buscar(filtro);
    }

    public ProdutoJogoController() {
        this.entidade = new Jogo();
        this.filtro = new Jogo();
        this.fotos = new LinkedList<>();
    }

    public void handleFileUpload(FileUploadEvent event) {
        if(event != null){
            fotos.add(event);
            this.MensagemSucesso("Sucesso!", "Imagem anexada com sucesso");
        }else{
            this.MensagemErro("Falha!", "Erro ao anexar o arquivo");
        }
    }

}
