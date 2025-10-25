package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.AvaliacaoDao;
import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
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
}
