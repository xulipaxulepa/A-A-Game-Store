/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeagamestore.persistencia;

import com.aeagamestore.entidade.FotoProduto;
import com.aeagamestore.entidade.Produto;
import com.aeagamestore.repositorios.ProdutoRepositorio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author arley
 */
@Singleton
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() {
        super(Produto.class);
    }

    @Override
    public List<Produto> Buscar(Produto filtro) {
        if (filtro != null) {
            return this.Like("nome", filtro.getNome())
                    .Like("nome", filtro.getDescricao())
                    .IgualA("id", filtro.getId())
                    .OrderBy("nome", "ASC").Buscar();

        }
        return this.Buscar();
    }

    @Override
    public boolean Atualizar(Produto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FotoProduto SalvarImagemDiretorio(InputStream is, String nome) {

        try {
            if (is == null || nome == null || nome.length() == 0) {
                return null;
            }

            String extension = nome.substring(nome.lastIndexOf("."));
            String name = java.util.UUID.randomUUID().toString() + extension;
            File file = new File("/home/arley/Projetos/A-A-Game-Store/Sistema_Web/AeAGameStore/web/resources/imagem/" + name);
            //File file = new File("/home/xulipaxulepa/Documentos/Apostilas (USER-PC)/5Quinto Semestre/Arquitetura de Software/A-A-Game-Store/Sistema_Web/AeAGameStore/web/resources/imagem/"+ name);
            try (OutputStream os = new FileOutputStream(file)) {
                byte buf[] = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0) {
                    os.write(buf, 0, len);
                }
            }
            is.close();
            FotoProduto foto = new FotoProduto();
            foto.setNome(name);
            return foto;

        } catch (IOException ex) {
            return null;
        }
    }

}
