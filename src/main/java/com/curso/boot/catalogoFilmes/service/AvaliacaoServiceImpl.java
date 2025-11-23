package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.AvaliacaoDao;
import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoDao dao;

    @Override
    public void save(Avaliacao avaliacao) {
        dao.save(avaliacao);
    }

    @Override
    public void update(Avaliacao avaliacao) {
        dao.update(avaliacao);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Avaliacao findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Avaliacao> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Avaliacao> findByFilmeId(Long filmeId) {
        return dao.findByFilmeId(filmeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Avaliacao findByFilmeIdAndUsuarioId(Long filmeId, Long usuarioId) {
        return dao.findByFilmeIdAndUsuarioId(filmeId, usuarioId);
    }
    @Override
    public Avaliacao findByComentarioId(Long comentarioId) {
        return dao.findByComentarioId(comentarioId);
    }
}
