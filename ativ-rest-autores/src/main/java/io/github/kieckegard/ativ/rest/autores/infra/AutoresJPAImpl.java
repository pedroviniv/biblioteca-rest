/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.autores.infra;

import io.github.kieckegard.ativ.rest.autores.model.Autor;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author kieckegard
 */

@RequestScoped
@Transactional
public class AutoresJPAImpl implements Autores {
    
    @PersistenceContext(unitName = "autores-pu")
    private EntityManager manager;

    @Override
    public void persist(Autor autor) {
        try {
            this.manager.persist(autor);
        } catch (EntityExistsException ex) {
            throw new EntityConflictException("There's already an autor"
                    + " with the email " + autor.getEmail(),ex);
        }
    }

    @Override
    public void remove(String email) {
        Autor autorFound = findByEmail(email);
        this.manager.remove(autorFound);
    }

    @Override
    public void update(Autor updatedAutor) {
        try {
            this.manager.merge(updatedAutor);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no autor with an email "
                    + updatedAutor.getEmail(), ex);
        }
    }

    @Override
    public Autor findByEmail(String email) {
        
        Optional<Autor> searchResult = Optional
                .ofNullable(this.manager.find(Autor.class, email));
        
        if(!searchResult.isPresent())
            throw new EntityNotFoundException("There's no autor with an email "
                    + email);
        
        return searchResult.get();
    }

    @Override
    public List<Autor> listAll() {
        return this.manager.createQuery("FROM Autor a", Autor.class)
                .getResultList();
    }
    
}
