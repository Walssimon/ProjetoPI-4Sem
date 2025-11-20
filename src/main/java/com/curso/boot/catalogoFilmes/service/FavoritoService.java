package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Favorito;
import java.util.List;

public interface FavoritoService {

    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Long id);

    Favorito findById(Long id);

    List<Favorito> findAll();
    
    // Novos métodos
    void favoritarFilme(Long usuarioId, Long filmeId);
    void removerFavorito(Long usuarioId, Long filmeId);
    List<Favorito> buscarFavoritosPorUsuario(Long usuarioId);
    boolean isFavoritado(Long usuarioId, Long filmeId);
}
