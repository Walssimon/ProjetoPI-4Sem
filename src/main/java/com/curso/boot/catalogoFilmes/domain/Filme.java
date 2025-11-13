package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_FILME")
public class Filme extends AbstractEntity<Long> {


    @Column(name = "NM_FILME", nullable = false,  length = 200)
    private String nomeFilme;

    @Column(name = "DS_SINOPSE", nullable = false,length = 500)
    private String descricao;

    @Column(name = "HR_DURACAO", nullable = false)
    private int duracao;

    @Column(name = "DT_LANCAMENTO", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "VL_AVALIACAO")
    private Double avaliacao;

    @Column(name = "NR_CLASSIFICACAO_INDICATIVA", length = 3)
    private Integer classificacaoIndicativa;

    // Relacionamento com TB_FILME_ATOR
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FilmeAtor> atores;

    // Relacionamento com TB_GENERO_FILME
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GeneroFilme> generos;

    // Getters e Setters
    public String getNomeFilme() { return nomeFilme; }
    public void setNomeFilme(String nomeFilme) { this.nomeFilme = nomeFilme; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public Double getAvaliacao() { return avaliacao; }
    public void setAvaliacao(Double avaliacao) { this.avaliacao = avaliacao; }

    public Integer getClassificacaoIndicativa() { return classificacaoIndicativa; }
    public void setClassificacaoIndicativa(Integer classificacaoIndicativa) { this.classificacaoIndicativa = classificacaoIndicativa; }

    public List<FilmeAtor> getAtores() { return atores; }
    public void setAtores(List<FilmeAtor> atores) { this.atores = atores; }

    public List<GeneroFilme> getGeneros() { return generos; }
    public void setGeneros(List<GeneroFilme> generos) { this.generos = generos; }
}
