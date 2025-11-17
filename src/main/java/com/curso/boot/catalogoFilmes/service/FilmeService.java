package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Imagem;

import java.util.List;

public interface FilmeService {
    void save(Filme filme);

    void update(Filme filme);

    void delete(Long id);

    Filme findById(Long id);

    List<Filme> findAll();


}
