package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_FILME_ATOR")
public class FilmeAtor extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FILME", nullable = false)
    private Filme filme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ATOR", nullable = false)
    private Ator ator;

    // se quiser, campos adicionais (por exemplo: papel do ator) podem ser adicionados aqui
    @Column(name = "NM_PAPEL", length = 200)
    private String papel;

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public Ator getAtor() { return ator; }
    public void setAtor(Ator ator) { this.ator = ator; }
    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}
