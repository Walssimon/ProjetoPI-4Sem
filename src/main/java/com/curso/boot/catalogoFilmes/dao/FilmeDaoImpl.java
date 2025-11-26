package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Genero;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmeDaoImpl extends AbstractDao<Filme, Long> implements FilmeDao {


    @Override
    public List<Filme> buscarPorNome(String nome) {
        return getEntityManager().createQuery(
                        "SELECT f FROM Filme f WHERE LOWER(f.nomeFilme) LIKE LOWER(:nome)",
                        Filme.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public List<Filme> buscarAleatorios(int quantidade) {
        return getEntityManager()
                .createQuery("SELECT f FROM Filme f ORDER BY function('RAND')", Filme.class)
                .setMaxResults(quantidade)
                .getResultList();
    }

}
