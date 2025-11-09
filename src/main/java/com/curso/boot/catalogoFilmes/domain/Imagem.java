package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "imagens")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    @Lob
    @Column(length = 16777215) // tamanho para armazenar blob médio (16MB)
    private byte[] dados;

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public byte[] getDados() { return dados; }

    public void setDados(byte[] dados) { this.dados = dados; }
}
