package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Ator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtorDaoImpl extends AbstractDao<Ator, Long> implements AtorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Ator findByNomeAtor(String nomeAtor) {
        // ATENÇÃO: Use 'nomeAtor' que é o nome da propriedade na entidade Ator
        String jpql = "SELECT a FROM Ator a WHERE a.nomeAtor = :nome";

        List<Ator> lista = em.createQuery(jpql, Ator.class)
                .setParameter("nome", nomeAtor)
                .getResultList();

        return lista.isEmpty() ? null : lista.get(0);
    }
}