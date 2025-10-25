package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Genero;
import org.springframework.stereotype.Repository;

@Repository
public class GeneroDaoImpl extends AbstractDao<Genero, Long> implements GeneroDao {
}
