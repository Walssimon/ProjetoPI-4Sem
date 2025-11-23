package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Genero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GeneroDaoImpl extends AbstractDao<Genero, Long> implements GeneroDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genero findByNome(String nome) {
        try {
            return em.createQuery(
                            "SELECT g FROM Genero g WHERE g.nomeGenero = :nome",
                            Genero.class)
                    .setParameter("nome", nome)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
}
