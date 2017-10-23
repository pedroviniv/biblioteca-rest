/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.infra;

import io.github.kieckegard.ativ.rest.livros.model.Livro;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface Livros {
    
    void persist(Livro livro);
    void remove(String isbn);
    void update(Livro updatedLivro);
    Livro findByIsbn(String isbn);
    List<Livro> listAll();
}
