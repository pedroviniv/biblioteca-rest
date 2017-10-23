/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.consumers;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class AutorConsumer {
    
    private String url = "http://autores-api:8080/ativ-rest-autores/api/autores";
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target(url);
    
    public boolean exists(String autorEmail) {
        
        Response getResponse = target.path(autorEmail)
                .request()
                .get();
        
        return getResponse.getStatus() != 404;
    }
}
