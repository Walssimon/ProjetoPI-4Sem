package com.curso.boot.catalogoFilmes.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario extends AbstractEntity<Long> {

    @Column(name = "NM_USUARIO", length = 200, nullable = false)
private String nome;

    @Column(name = "DS_EMAIL", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "DS_SENHA", length = 200, nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
