/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.autores.infra;

import io.github.kieckegard.ativ.rest.autores.model.Autor;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface Autores {
    
    void persist(Autor autor);
    void remove(String email);
    void update(Autor updatedAutor);
    Autor findByEmail(String email);
    List<Autor> listAll();
}
