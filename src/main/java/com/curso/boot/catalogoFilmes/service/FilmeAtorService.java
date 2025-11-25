package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import java.util.List;

public interface FilmeAtorService {

    void save(FilmeAtor filmeAtor);

    void update(FilmeAtor filmeAtor);

    void delete(Long id);

    FilmeAtor findById(Long id);

    List<FilmeAtor> findAll();

    List<FilmeAtor> findByFilmeId(Long filmeId);

    List<FilmeAtor> deleteByFilmeId(Long id);
    void deleteAllByFilmeId(Long filmeId);
}
