package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Long id);

    Usuario findById(Long id);

    List<Usuario> findAll();
}
