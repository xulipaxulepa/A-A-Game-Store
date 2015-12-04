
package com.aeagamestore.persistencia;


import com.aeagamestore.entidade.Repositorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
public abstract class DAOGenerico<T> implements Repositorio<T>{

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("A_AGameStorePU");
    protected EntityManager manager = factory.createEntityManager();
    Class classe;
    
    /**
     *
     * @return
     */

    public DAOGenerico(Class t){
        this.classe = t;
    }
    
    @Override
    public boolean Salvar(T obj) {
        EntityTransaction t = manager.getTransaction();
        
        try {            

            t.begin();

            manager.persist(obj);

            t.commit();
            return true;
        
        } catch (Exception e){
            t.rollback();
            return false;
        }
    }

    @Override
    public T Abrir(Long id) {
        return (T) manager.find(classe, id);
    }

    @Override
    public boolean Apagar(T obj) {
         EntityTransaction t = manager.getTransaction();
        
        try {            

            t.begin();

            manager.remove(obj);

            t.commit();
            return true;
        
        } catch (Exception e){
            t.rollback();
            return false;
        }
    }

    
    @Override
    public abstract List<T> Buscar(T filtro);
    
    
}
