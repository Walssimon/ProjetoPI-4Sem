package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import java.util.List;

public interface GeneroFilmeDao {
    void save(GeneroFilme generoFilme);

    void update(GeneroFilme generoFilme);

    void delete(Long id);

    GeneroFilme findById(Long id);

    List<GeneroFilme> findAll();

    List<GeneroFilme> findByGeneroId(Long generoId);

    List<GeneroFilme> findByFilmeId(Long filmeId);

    void deleteByFilmeId(Long id);
}
