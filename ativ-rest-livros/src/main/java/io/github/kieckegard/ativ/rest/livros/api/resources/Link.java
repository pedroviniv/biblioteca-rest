/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.api.resources;

import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public class Link implements Serializable {
    
    private String rel;
    private String href;

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public Link() {
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link{" + "rel=" + rel + ", href=" + href + '}';
    }
}
