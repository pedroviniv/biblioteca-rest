/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.api;

import io.github.kieckegard.ativ.rest.livros.infra.Livros;
import io.github.kieckegard.ativ.rest.livros.model.Autor;
import io.github.kieckegard.ativ.rest.livros.model.Livro;
import io.github.kieckegard.ativ.rest.livros.services.LivroAutoresService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class LivroAutoresEndPoint {
    
    @Inject private LivroAutoresService livroAutores;
    
    @POST
    @Path("{autorEmail}")
    public Response addAutor(
            @DefaultValue("-1") @PathParam("livroIsbn") String livroIsbn,
            @DefaultValue("-1") @PathParam("autorEmail") String autorEmail) {
        
        this.livroAutores.bind(livroIsbn, autorEmail);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{autorEmail}")
    public Response removeAutor(
            @DefaultValue("-1") @PathParam("livroIsbn") String livroIsbn,
            @DefaultValue("-1") @PathParam("autorEmail") String autorEmail) {
        
        this.livroAutores.unbind(livroIsbn, autorEmail);
        
        return Response.ok().build();
    }
}
