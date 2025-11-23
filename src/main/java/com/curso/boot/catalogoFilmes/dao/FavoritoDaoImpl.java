package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Favorito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritoDaoImpl extends AbstractDao<Favorito, Long> implements FavoritoDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> buscarIdsFavoritos(Long usuarioId) {
        String jpql = "SELECT f.filme.id FROM Favorito f WHERE f.usuario.id = :usuarioId";

        return em.createQuery(jpql)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }
    @Override
    public Favorito findByUsuarioAndFilme(Long usuarioId, Long filmeId) {
        String jpql = "SELECT f FROM Favorito f WHERE f.usuario.id = :usuarioId AND f.filme.id = :filmeId";
        TypedQuery<Favorito> query = getEntityManager().createQuery(jpql, Favorito.class);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("filmeId", filmeId);
        
        List<Favorito> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    
    @Override
    public List<Favorito> findByUsuarioId(Long usuarioId) {
        String jpql = "SELECT f FROM Favorito f WHERE f.usuario.id = :usuarioId";
        TypedQuery<Favorito> query = getEntityManager().createQuery(jpql, Favorito.class);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }
    
    @Override
    public boolean existsByUsuarioAndFilme(Long usuarioId, Long filmeId) {
        return findByUsuarioAndFilme(usuarioId, filmeId) != null;
    }
}
