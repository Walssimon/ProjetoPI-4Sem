package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Comentario;
import java.util.List;

public interface ComentarioService {

    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Long id);

    Comentario findById(Long id);

    List<Comentario> findAll();
}
