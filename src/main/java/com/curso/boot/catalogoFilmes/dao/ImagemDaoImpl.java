package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Imagem;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ImagemDaoImpl implements ImagemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Imagem imagem) {
        em.persist(imagem);
    }
}
