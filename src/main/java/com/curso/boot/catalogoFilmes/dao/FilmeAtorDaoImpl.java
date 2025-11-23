package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmeAtorDaoImpl extends AbstractDao<FilmeAtor, Long> implements FilmeAtorDao {

    @Override
    public List<FilmeAtor> findByFilmeId(Long filmeId) {
        return createQuerry(
                "select fa from FilmeAtor fa where fa.filme.id = ?1",
                filmeId
        );
    }
}

