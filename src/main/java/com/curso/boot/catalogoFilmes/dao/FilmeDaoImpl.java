package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Filme;
import org.springframework.stereotype.Repository;

@Repository
public class FilmeDaoImpl extends AbstractDao<Filme, Long> implements FilmeDao {
}
