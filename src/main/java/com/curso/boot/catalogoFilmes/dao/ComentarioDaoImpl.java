package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Comentario;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioDaoImpl extends AbstractDao<Comentario, Long> implements ComentarioDao {
}
