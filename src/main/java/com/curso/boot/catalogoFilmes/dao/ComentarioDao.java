package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Comentario;
import java.util.List;

public interface ComentarioDao {

    void save(Comentario comentario);
    void update(Comentario comentario);
    void delete(Long id);

    Comentario findById(Long id);

    List<Comentario> findAll();

    List<Comentario> findByFilmeId(Long filmeId);
    
}
