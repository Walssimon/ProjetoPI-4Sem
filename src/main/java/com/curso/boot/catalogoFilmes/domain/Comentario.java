package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_COMENTARIO")
public class Comentario extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "ID_FILME", nullable = false)
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "DS_COMENTARIO", length = 400, nullable = false)
    private String texto;

    @Column(name = "DT_COMENTARIO", nullable = false)
    private LocalDate dataComentario;

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDate getDataComentario() { return dataComentario; }
    public void setDataComentario(LocalDate dataComentario) { this.dataComentario = dataComentario; }
}
