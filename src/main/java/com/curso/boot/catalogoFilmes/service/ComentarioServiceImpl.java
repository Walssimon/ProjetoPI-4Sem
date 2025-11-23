package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.ComentarioDao;
import com.curso.boot.catalogoFilmes.domain.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioDao dao;

    @Override
    public void save(Comentario comentario) {
        dao.save(comentario);
    }

    @Override
    public void update(Comentario comentario) {
        dao.update(comentario);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findByFilmeId(Long filmeId) {
        return dao.findByFilmeId(filmeId);
    }
}
