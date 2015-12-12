/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Endereco;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.persistencia.ClienteDAO;
import java.util.Date;


/**
 *
 * @author Arley
 */
public class Testes {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
         Cliente arley = new Cliente();
         
         arley = (Cliente) arley;
         
         Endereco e = new Endereco();
         e.setBairro("Cidade Nova");
         e.setCidade("Januária");
         e.setRua("I");
         e.setNumero(25);
         e.setEstado("MG");
         
         
        arley.setEndereco(e);
        arley.setCpf("1010101");
        arley.setNome("Arley");
        arley.setDataNascimento(new Date());
        arley.setEmail("arley.msn@hotmail.com");
        arley.setSenha("10101");
        arley.addTelefone(new Telefone("38 99802829", "Celular"));
        
        ClienteDAO pdao = new ClienteDAO();
        pdao.Salvar(arley);
        
    }
}
