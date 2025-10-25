package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import org.springframework.stereotype.Repository;

@Repository
public class AvaliacaoDaoImpl extends AbstractDao<Avaliacao, Long> implements AvaliacaoDao {
}
