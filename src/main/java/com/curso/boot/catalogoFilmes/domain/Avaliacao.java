package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_AVALIACAO_USUARIO_FILME")
public class Avaliacao extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_FILME", nullable = false)
    private Filme filme;

    @Column(name = "NOTA", scale = 1, nullable = false)
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentario;


    public Comentario getComentario() { return comentario; }
    public void setComentario(Comentario comentario) { this.comentario = comentario; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
}
