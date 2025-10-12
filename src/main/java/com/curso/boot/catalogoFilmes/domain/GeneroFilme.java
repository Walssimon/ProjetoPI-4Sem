package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_GENERO_FILME")
public class GeneroFilme extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FILME", nullable = false)
    private Filme filme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_GENERO", nullable = false)
    private Genero genero;

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
}
