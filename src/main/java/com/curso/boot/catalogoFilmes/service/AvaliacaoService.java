package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AvaliacaoService {

    void save (Avaliacao avaliacao);

    void update(Avaliacao avaliacao);

    void delete(Long id);

    Avaliacao findById(Long id);

    List<Avaliacao> findAll();

    List<Avaliacao> findByFilmeId(Long filmeId);

    @Transactional(readOnly = true)
    Avaliacao findByFilmeIdAndUsuarioId(Long filmeId, Long usuarioId);

    Avaliacao findByComentarioId(Long comentarioId);

}
