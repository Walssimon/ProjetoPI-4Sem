package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import java.util.List;

public interface AvaliacaoService {

    void save (Avaliacao avaliacao);

    void update(Avaliacao avaliacao);

    void delete(Long id);

    Avaliacao findById(Long id);

    List<Avaliacao> findAll();
}
