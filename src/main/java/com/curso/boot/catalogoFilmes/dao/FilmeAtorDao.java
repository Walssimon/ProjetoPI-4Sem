package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import java.util.List;

public interface FilmeAtorDao {
    void save(FilmeAtor filmeAtor);

    void update(FilmeAtor filmeAtor);

    void delete(Long id);

    FilmeAtor findById(Long id);

    List<FilmeAtor> findAll();

    List<FilmeAtor> findByFilmeId(Long filmeId);

}
