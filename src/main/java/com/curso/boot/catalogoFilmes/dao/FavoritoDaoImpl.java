package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Favorito;
import org.springframework.stereotype.Repository;

@Repository
public class FavoritoDaoImpl extends AbstractDao<Favorito, Long> implements FavoritoDao {
}
