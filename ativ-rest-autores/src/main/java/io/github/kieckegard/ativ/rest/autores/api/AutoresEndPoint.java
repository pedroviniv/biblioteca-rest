/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.autores.api;

import io.github.kieckegard.ativ.rest.autores.infra.Autores;
import io.github.kieckegard.ativ.rest.autores.model.Autor;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

@Path("autores")
@RequestScoped
public class AutoresEndPoint {
    
    @Inject private Autores autores;
    
    @GET
    @Path("{autorEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(
            @DefaultValue("-1") 
            @PathParam("autorEmail") 
                    String email) {
        return Response.ok(autores.findByEmail(email)).build();
    }
    
    @GET
    public Response listAll() {
        
        GenericEntity<List<Autor>> entity = 
                new GenericEntity<List<Autor>>(this.autores.listAll()){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Autor autor, @Context UriInfo uriInfo) {
        
        this.autores.persist(autor);
        
        URI uri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(autor.getEmail())
                .build();
        
        return Response.created(uri).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Autor autor) {
        
        this.autores.update(autor);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{autorEmail}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("autorEmail") 
                    String email) {
        
        this.autores.remove(email);
        
        return Response.ok().build();
    }
}
