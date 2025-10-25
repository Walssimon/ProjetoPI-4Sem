package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import java.util.List;

public interface FilmeAtorService {

    void save(FilmeAtor filmeAtor);

    void update(FilmeAtor filmeAtor);

    void delete(Long id);

    FilmeAtor findById(Long id);

    List<FilmeAtor> findAll();
}
