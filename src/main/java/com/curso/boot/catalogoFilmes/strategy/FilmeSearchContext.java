package com.curso.boot.catalogoFilmes.strategy;

import com.curso.boot.catalogoFilmes.domain.Filme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmeSearchContext {

    private FilmeSearchStrategy strategy;

    public void setStrategy(FilmeSearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Filme> buscarFilmes() {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de busca definida");
        }
        return strategy.buscar();
    }
}
