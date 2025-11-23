package com.curso.boot.catalogoFilmes.strategy;

import com.curso.boot.catalogoFilmes.domain.Filme;
import java.util.List;

public interface FilmeSearchStrategy {
    List<Filme> buscar();
}
