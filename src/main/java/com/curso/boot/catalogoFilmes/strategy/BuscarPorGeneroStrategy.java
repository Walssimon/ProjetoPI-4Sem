package com.curso.boot.catalogoFilmes.strategy;

import com.curso.boot.catalogoFilmes.dao.GeneroDao;
import com.curso.boot.catalogoFilmes.dao.GeneroFilmeDao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Genero;
import com.curso.boot.catalogoFilmes.domain.GeneroFilme;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarPorGeneroStrategy implements FilmeSearchStrategy {

    private final String generoNome;
    private final GeneroDao generoDao;
    private final GeneroFilmeDao generoFilmeDao;

    public BuscarPorGeneroStrategy(String generoNome,
                                   GeneroDao generoDao,
                                   GeneroFilmeDao generoFilmeDao) {
        this.generoNome = generoNome;
        this.generoDao = generoDao;
        this.generoFilmeDao = generoFilmeDao;
    }

    @Override
    public List<Filme> buscar() {
        // 1) buscar o gênero
        Genero genero = generoDao.findByNome(generoNome);
        if (genero == null) {
            return List.of();
        }

        // 2) buscar as relações genero-filme
        List<GeneroFilme> relacoes = generoFilmeDao.findByGeneroId(genero.getId());

        // 3) extrair apenas os filmes
        return relacoes.stream()
                .map(GeneroFilme::getFilme)
                .collect(Collectors.toList());
    }
}
