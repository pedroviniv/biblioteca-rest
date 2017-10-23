/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.services;

import io.github.kieckegard.ativ.rest.livros.consumers.AutorConsumer;
import io.github.kieckegard.ativ.rest.livros.infra.EntityNotFoundException;
import io.github.kieckegard.ativ.rest.livros.infra.Livros;
import io.github.kieckegard.ativ.rest.livros.model.Autor;
import io.github.kieckegard.ativ.rest.livros.model.Livro;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class LivroAutoresService {
    
    @Inject private Livros livros;
    @Inject private AutorConsumer autorConsumer;
    
    private void updateLivro(Livro livro) {
        this.livros.update(livro);
    }
    
    private Livro getLivroByIsbn(String isbn) {
        return livros.findByIsbn(isbn);
    }
    
    public void bind(String livroIsbn, String autorEmail) {
        
        Livro livroFound = getLivroByIsbn(livroIsbn);

        if(!this.autorConsumer.exists(autorEmail)) {
            throw new EntityNotFoundException("There's no author with the email "
                    + autorEmail);
        }
        
        livroFound.addAutor(new Autor(autorEmail));
        
        this.livros.update(livroFound);
    }
    
    public void unbind(String livroIsbn, String autorEmail) {
        
        Livro livroFound = getLivroByIsbn(livroIsbn);
        
        livroFound.removeAutor(new Autor(autorEmail));
        
        updateLivro(livroFound);
    }
    
}
