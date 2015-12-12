
package com.aeagamestore.entidade.repositorios;

import com.aeagamestore.entidade.Pessoa;

/**
 *
 * @author Arley
 */
public interface PessoaRepositorio extends Repositorio<Pessoa> {
    public Pessoa Abrir(String cpf);
}
