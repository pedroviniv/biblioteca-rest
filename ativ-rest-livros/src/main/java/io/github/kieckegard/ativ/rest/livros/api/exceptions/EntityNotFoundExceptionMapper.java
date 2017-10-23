/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.api.exceptions;

import io.github.kieckegard.ativ.rest.livros.infra.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kieckegard
 */

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        Response.Status NOT_FOUND = Response.Status.NOT_FOUND;
        ErrorResponse errorMessage = 
                new ErrorResponse(NOT_FOUND.getStatusCode(), exception.getMessage());
        
        return Response.status(NOT_FOUND).entity(errorMessage).build();
    }
    
}
