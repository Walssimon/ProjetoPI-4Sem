package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Genero;
import java.util.List;

public interface GeneroService {
    void save(Genero genero);

    void update(Genero genero);

    void delete(Long id);

    Genero findById(Long id);

    List<Genero> findAll();
}
