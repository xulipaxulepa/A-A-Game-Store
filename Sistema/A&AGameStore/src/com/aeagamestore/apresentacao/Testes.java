/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Fornecedor;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.persistencia.FornecedorDAO;

/**
 *
 * @author Arley
 */
public class Testes {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Fornecedor f = new Fornecedor();
         
         f.setCnpj("101010");
         f.setNome("MicroSoft");
         Telefone t = new Telefone("38-91080138", "Celular");
         
         f.addTelefone(t);
         
         FornecedorDAO fdao = new FornecedorDAO();
         
         fdao.Salvar(f);
         
         
         
    }
}
