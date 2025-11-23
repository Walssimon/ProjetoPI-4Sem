package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.AtorDao;
import com.curso.boot.catalogoFilmes.domain.Ator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class AtorServiceImpl implements AtorService {

    @Autowired
    private AtorDao dao;

    @Override
    public void save(Ator ator) {
        dao.save(ator);
    }

    @Override
    public void update(Ator ator) {
        dao.update(ator);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Ator findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ator> findAll() {
        return dao.findAll();
    }

    @Override
    public Ator findByNomeAtor(String nomeAtor) {
        return dao.findByNomeAtor(nomeAtor);
    }
}
