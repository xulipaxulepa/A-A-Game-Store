/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.repositorios;

import com.aeagamestore.entidade.FotoProduto;
import com.aeagamestore.entidade.Produto;
import java.io.InputStream;

/**
 *
 * @author arley
 */
public interface ProdutoRepositorio extends Repositorio<Produto>{
    
    public FotoProduto SalvarImagemDiretorio(InputStream is, String nome);
    
}
