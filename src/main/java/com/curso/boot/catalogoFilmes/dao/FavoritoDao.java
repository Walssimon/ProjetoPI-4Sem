package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Favorito;
import java.util.List;

public interface FavoritoDao {
    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Long id);

    Favorito findById(Long id);

    List<Favorito> findAll();
}
