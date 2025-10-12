package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_FAVORITO")
public class Favorito extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "ID_FILME", nullable = false)
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
