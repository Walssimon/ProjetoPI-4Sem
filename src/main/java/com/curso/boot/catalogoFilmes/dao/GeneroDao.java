package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Genero;
import java.util.List;

public interface GeneroDao {
    void save(Genero genero);

    void update(Genero genero);

    void delete(Long id);

    Genero findById(Long id);

    List<Genero> findAll();

    Genero findByNome(String genero);
}
