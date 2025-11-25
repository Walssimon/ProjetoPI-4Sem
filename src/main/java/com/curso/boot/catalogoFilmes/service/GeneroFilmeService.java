package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import java.util.List;

public interface GeneroFilmeService {
    void save(GeneroFilme generoFilme);

    void update(GeneroFilme generoFilme);

    void delete(Long id);

    GeneroFilme findById(Long id);

    List<GeneroFilme> findAll();
    void deleteByFilmeId(Long id);

}
