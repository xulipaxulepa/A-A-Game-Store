
package com.aeagamestore.repositorios;

import com.aeagamestore.entidade.Pessoa;
import javax.ejb.Local;

/**
 *
 * @author Arley
 */
@Local
public interface PessoaRepositorio extends Repositorio<Pessoa> {
    public Pessoa Abrir(String cpf);
}
