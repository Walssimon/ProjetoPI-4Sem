package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "TB_ATOR")
public class Ator extends AbstractEntity<Long> {

    @Column(name = "NM_ATOR", length = 200, nullable = false)
    private String nomeAtor;

    @Column(name = "DT_IDADE", nullable = false)
    private LocalDate dataNascimento;

    @Lob
    @Column(length = 16777215) // tamanho para armazenar blob médio (16MB)
    private byte[] dados;

    // Relacionamento com a entidade filha FilmeAtor
    @OneToMany(mappedBy = "ator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FilmeAtor> filmes;

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public String getFotoBase64() {
        if (dados == null) return null;
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(dados);
    }


    public String getNomeAtor() { return nomeAtor; }
    public void setNomeAtor(String nomeAtor) { this.nomeAtor = nomeAtor; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public List<FilmeAtor> getFilmes() { return filmes; }
    public void setFilmes(List<FilmeAtor> filmes) { this.filmes = filmes; }
}
