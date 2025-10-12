package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_GENERO")
public class Genero extends AbstractEntity<Long> {

    @Column(name = "NM_GENERO", length = 200, nullable = false)
    private String nomeGenero;

    // Relacionamento com a entidade filha GeneroFilme
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GeneroFilme> filmes;

    public String getNomeGenero() { return nomeGenero; }
    public void setNomeGenero(String nomeGenero) { this.nomeGenero = nomeGenero; }
    public List<GeneroFilme> getFilmes() { return filmes; }
    public void setFilmes(List<GeneroFilme> filmes) { this.filmes = filmes; }
}
