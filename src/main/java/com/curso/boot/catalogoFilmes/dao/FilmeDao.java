package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Genero;

import java.util.List;

public interface FilmeDao {

    void save(Filme filme);

    void update(Filme filme);

    void delete(Long id);

    Filme findById(Long id);

    List<Filme> findAll();

}
