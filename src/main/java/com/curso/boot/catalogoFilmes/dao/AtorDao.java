package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Ator;
import java.util.List;

public interface AtorDao {
    void save(Ator ator);

    void update(Ator ator);

    void delete(Long id);

    Ator findById(Long id);

    List<Ator> findAll();
}
