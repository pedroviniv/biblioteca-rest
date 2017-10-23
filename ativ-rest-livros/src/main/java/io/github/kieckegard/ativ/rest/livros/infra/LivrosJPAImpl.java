/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.infra;

import io.github.kieckegard.ativ.rest.livros.model.Livro;
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
public class LivrosJPAImpl implements Livros {
    
    @PersistenceContext(name = "livros-pu")
    private EntityManager manager;

    @Override
    public void persist(Livro livro) {
        try {
            this.manager.persist(livro);
        } catch (EntityExistsException ex) {
            throw new EntityNotFoundException("There's already a book with isbn "
                    + livro.getIsbn(),ex);
        }
    }

    @Override
    public void remove(String isbn) {
        Livro found = findByIsbn(isbn);
        this.manager.remove(found);
    }

    @Override
    public void update(Livro updatedLivro) {
        try {
            this.manager.merge(updatedLivro);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no a book with isbn "
                    + updatedLivro.getIsbn(), ex);
        }
    }

    @Override
    public Livro findByIsbn(String isbn) {
        
        Optional<Livro> searchResult = Optional
                .ofNullable(this.manager.find(Livro.class, isbn));
        
        if(!searchResult.isPresent())
            throw new EntityNotFoundException("There's no a book with isbn "
                    + isbn);
        
        return searchResult.get();
    }

    @Override
    public List<Livro> listAll() {
        return this.manager.createQuery("FROM Livro l", Livro.class)
                .getResultList();
    }
    
}
