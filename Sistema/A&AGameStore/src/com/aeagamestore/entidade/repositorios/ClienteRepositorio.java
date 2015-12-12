
package com.aeagamestore.entidade.repositorios;

import com.aeagamestore.entidade.Cliente;
import com.aeagamestore.entidade.Pessoa;

/**
 *
 * @author petronio
 */
public interface ClienteRepositorio extends Repositorio<Cliente> {
    public Pessoa Abrir(String cpf);
}
