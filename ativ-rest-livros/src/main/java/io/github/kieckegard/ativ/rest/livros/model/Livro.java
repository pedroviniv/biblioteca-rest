/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.ativ.rest.livros.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

/**
 *
 * @author kieckegard
 */

@Entity
public class Livro implements Serializable {
    
    @Id
    private String isbn;
    private String titulo;
    private String descricao;
    private Integer edicao;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autores",
            joinColumns = @JoinColumn(name = "livro_isbn")
    )
    private List<Autor> autores;

    public Livro(String isbn, String titulo, String descricao, Integer edicao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descricao = descricao;
        this.edicao = edicao;
        this.autores = new ArrayList<>();
    }

    public Livro() {
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    public void addAutor(Autor autor) {
        this.autores.add(autor);
    }
    
    public void removeAutor(Autor autor) {
        this.autores.remove(autor);
    }

    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn + ", titulo=" + titulo + ", descricao=" + descricao + ", edicao=" + edicao + '}';
    }
}
