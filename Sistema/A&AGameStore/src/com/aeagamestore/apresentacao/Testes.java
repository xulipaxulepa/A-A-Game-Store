/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.apresentacao;

import com.aeagamestore.entidade.Cargo;
import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Endereco;
import com.aeagamestore.entidade.Funcionario;
import com.aeagamestore.entidade.Pessoa;
import com.aeagamestore.entidade.Telefone;
import com.aeagamestore.persistencia.ClienteDAO;
import com.aeagamestore.persistencia.FuncionarioDAO;
import com.aeagamestore.persistencia.PessoaDAO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Arley
 */
public class Testes {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Funcionario f = new Funcionario();
       
        List<Funcionario> funcionarios;
        
        FuncionarioDAO fdao = new FuncionarioDAO();
        funcionarios = fdao.Buscar(null);
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() + " - " + funcionario.getSalario() + " - " + funcionario.getCargo().getNome());
        }
        
        /* 
        f.setCpf("2020202");
        f.setNome("João");
        f.setDataNascimento(new Date());
        f.setEmail("joao.msn@gmail.com");
        f.setSenha("2020201");
        f.setSalario(new BigDecimal("5000.00"));
        f.addTelefone(new Telefone("38 99802829", "Celular"));
        
        f.setCargo(new Cargo("Atendente", "adafda"));
        
        
        FuncionarioDAO fdao = new FuncionarioDAO();
        fdao.Salvar(f);*/
        
        
         /*
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
        arley.addTelefone(new Telefone("38 99802829", "Celular"));*/
        
        ClienteDAO pdao = new ClienteDAO();
        //pdao.Salvar(arley);
    
        
        PessoaDAO pdao1 = new PessoaDAO();
        
        ClienteDAO cdao = new ClienteDAO();
        
        /*
        List<Pessoa> pessoas = pdao1.Buscar(null);
        for (Pessoa p : pessoas) {
            p = (Cliente) p;
            System.out.println(p.getNome());
        }
        
        List<Cliente> clientes = cdao.Buscar(null);
        for (Cliente c : clientes) {
            c = (Cliente) c;
            System.out.println(c.getNome());
        }
        */
        
        Cliente c1 = new Cliente();
        c1.setNome("Arley");
        c1.setCpf("1010101");
        List<Cliente> cliente = cdao.Buscar(c1);
        System.out.println("\n");
        for (Cliente cliente1 : cliente) {
            System.out.println(cliente1.getNome());
        }
        
        Cliente c = cdao.Abrir("1010101");
        
        c.setSexo('M');
        c.setNome("Arley Oliveira");
        
        c.getEndereco().setCidade("Januária");
        
        cdao.Atualizar(c);
        
        c = cdao.Abrir("1010101");
        
        
        
        
        System.out.println(c.getSexo() + " - " + c.getNome());
    }
}
