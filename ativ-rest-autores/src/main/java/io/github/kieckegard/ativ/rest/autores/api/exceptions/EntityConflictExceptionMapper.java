/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.autores.api.exceptions;

import io.github.kieckegard.ativ.rest.autores.infra.EntityConflictException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kieckegard
 */

@Provider
public class EntityConflictExceptionMapper implements ExceptionMapper<EntityConflictException> {

    @Override
    public Response toResponse(EntityConflictException exception) {
        Response.Status CONFLICT = Response.Status.CONFLICT;
        ErrorResponse errorMessage = 
                new ErrorResponse(CONFLICT.getStatusCode(), exception.getMessage());
        
        return Response.status(CONFLICT).entity(errorMessage).build();
    }
    
}
