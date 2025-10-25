package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import java.util.List;

public interface AvaliacaoDao {
    void save(Avaliacao avaliacao);

    void update(Avaliacao avaliacao);

    void delete(Long id);

    Avaliacao findById(Long id);

    List<Avaliacao> findAll();
}
