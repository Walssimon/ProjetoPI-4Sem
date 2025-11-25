package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeneroFilmeDaoImpl extends AbstractDao<GeneroFilme, Long> implements GeneroFilmeDao {

    @Override
    public List<GeneroFilme> findByGeneroId(Long generoId) {
        return createQuerry(
                "select gf from GeneroFilme gf where gf.genero.id = ?1 order by gf.id desc",
                generoId
        );
    }

    @Override
    public List<GeneroFilme> findByFilmeId(Long filmeId) {
        return createQuerry(
                "select gf from GeneroFilme gf where gf.filme.id = ?1 order by gf.id desc",
                filmeId
        );
    }


    @Override
    public void deleteByFilmeId(Long filmeId) {
        executeUpdate("delete from GeneroFilme gf where gf.filme.id = ?1", filmeId);
    }
}
