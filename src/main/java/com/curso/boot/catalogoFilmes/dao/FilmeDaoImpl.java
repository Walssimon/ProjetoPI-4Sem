package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Genero;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmeDaoImpl extends AbstractDao<Filme, Long> implements FilmeDao {

}
