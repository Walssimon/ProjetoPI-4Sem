package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import org.springframework.stereotype.Repository;

@Repository
public class GeneroFilmeDaoImpl extends AbstractDao<GeneroFilme, Long> implements GeneroFilmeDao {
}
