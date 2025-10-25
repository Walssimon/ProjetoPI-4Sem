package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Ator;

import java.util.List;

public interface AtorService {

    void save(Ator ator);

    void update(Ator ator);

    void delete(Long id);

    Ator findById(Long id);

    List<Ator> findAll();

}
