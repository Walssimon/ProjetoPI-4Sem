package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_ATOR")
public class Ator extends AbstractEntity<Long> {

    @Column(name = "NM_ATOR", length = 200, nullable = false)
    private String nomeAtor;

    @Column(name = "DT_IDADE", nullable = false)
    private LocalDate dataNascimento;

    // Relacionamento com a entidade filha FilmeAtor
    @OneToMany(mappedBy = "ator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FilmeAtor> filmes;

    public String getNomeAtor() { return nomeAtor; }
    public void setNomeAtor(String nomeAtor) { this.nomeAtor = nomeAtor; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public List<FilmeAtor> getFilmes() { return filmes; }
    public void setFilmes(List<FilmeAtor> filmes) { this.filmes = filmes; }
}
