
package com.aeagamestore.entidade;

/**
 *
 * @author Arley
 */
public interface PessoaRepositorio extends Repositorio<Pessoa> {
    public Pessoa Abrir(String cpf);
}
