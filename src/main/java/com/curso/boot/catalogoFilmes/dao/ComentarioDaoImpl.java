package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Comentario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComentarioDaoImpl extends AbstractDao<Comentario, Long> implements ComentarioDao {

    @Override
    public List<Comentario> findByFilmeId(Long filmeId) {
        return createQuerry(
                "select c from Comentario c where c.filme.id = ?1 order by c.dataComentario desc",
                filmeId
        );
    }
}
