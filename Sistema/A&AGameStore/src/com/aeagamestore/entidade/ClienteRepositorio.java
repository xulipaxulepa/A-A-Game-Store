
package com.aeagamestore.entidade;

/**
 *
 * @author petronio
 */
public interface ClienteRepositorio extends Repositorio<Cliente> {
    public Pessoa Abrir(String cpf);
}
