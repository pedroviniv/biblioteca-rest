/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.api;

import io.github.kieckegard.ativ.rest.livros.api.resources.LivroResource;
import io.github.kieckegard.ativ.rest.livros.infra.Livros;
import io.github.kieckegard.ativ.rest.livros.model.Livro;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@Path("livro")
@RequestScoped
public class LivrosEndPoint {
    
    @Inject
    private Livros livros;
    @Inject
    private LivroAutoresEndPoint livroAutoresEndPoint;
    
    @GET
    @Path("{livroIsbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIsbn(
            @DefaultValue("-1") 
            @PathParam("livroIsbn") 
                    String livroIsbn) {
        
        Livro found = this.livros.findByIsbn(livroIsbn);
        
        return Response.ok(LivroResource.of(found)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        
        List<Livro> livrosFound = this.livros.listAll();
        
        List<LivroResource> livrosResourceFound = livrosFound.stream()
                .map(l -> LivroResource.of(l))
                .collect(Collectors.toList());
        
        GenericEntity<List<LivroResource>> entity = 
                new GenericEntity<List<LivroResource>>(livrosResourceFound){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Livro livro, @Context UriInfo uriInfo) {
        
        this.livros.persist(livro);
        
        URI uri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(livro.getIsbn())
                .build();
        
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{livroIsbn}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("livroIsbn") 
                    String livroIsbn) {
        
        this.livros.remove(livroIsbn);
        
        return Response.ok().build();
    }
    
    @Path("{livroIsbn}/autores")
    public LivroAutoresEndPoint autoresSubr() {
        return livroAutoresEndPoint;
    }
    
    
}
