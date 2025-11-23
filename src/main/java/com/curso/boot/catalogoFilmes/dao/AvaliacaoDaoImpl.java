package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class AvaliacaoDaoImpl extends AbstractDao<Avaliacao, Long> implements AvaliacaoDao {

    @Override
    public List<Avaliacao> findByFilmeId(Long filmeId) {
        return createQuerry("select a from Avaliacao a where a.filme.id = ?1", filmeId);
    }
    @Override
    public Avaliacao findByComentarioId(Long comentarioId) {
        List<Avaliacao> list = createQuerry(
                "select a from Avaliacao a where a.comentario.id = ?1",
                comentarioId
        );

        return list.isEmpty() ? null : list.get(0);
    }
    @Override
    public Avaliacao findByFilmeIdAndUsuarioId(Long filmeId, Long usuarioId) {
        List<Avaliacao> list = createQuerry(
                "select a from Avaliacao a where a.filme.id = ?1 and a.usuario.id = ?2",
                filmeId, usuarioId
        );

        return list.isEmpty() ? null : list.get(0);
    }
}
