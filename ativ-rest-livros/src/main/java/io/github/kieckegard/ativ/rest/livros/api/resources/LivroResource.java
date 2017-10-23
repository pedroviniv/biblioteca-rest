/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.api.resources;

import io.github.kieckegard.ativ.rest.livros.model.Livro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kieckegard
 */
public class LivroResource implements Serializable {
    
    private String isbn;
    private String titulo;
    private String descricao;
    private Integer edicao;
    private List<Link> clientes;

    public LivroResource(String isbn, String titulo, String descricao, Integer edicao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descricao = descricao;
        this.edicao = edicao;
        this.clientes = new ArrayList<>();
    }
    
    public static LivroResource of(Livro livro) {
        
        String autoresApi = "http://autores-api:8080/ativ-rest-autores/api/autores/";
        
        LivroResource res = new LivroResource(
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getDescricao(),
                livro.getEdicao()
        );
        
        List<Link> autores = livro.getAutores()
                .stream()
                .map((a) -> {
                    String email = a.getEmail();
                    String href =  autoresApi + email;
                    return new Link(email, href); 
                })
                .collect(Collectors.toList());
        
        res.setClientes(autores);
        
        return res;
    }

    public LivroResource() {
        this.clientes = new ArrayList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public List<Link> getClientes() {
        return clientes;
    }

    public void setClientes(List<Link> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "LivroResource{" + "isbn=" + isbn + ", titulo=" + titulo + ", descricao=" + descricao + ", edicao=" + edicao + ", clientes=" + clientes + '}';
    }
}
