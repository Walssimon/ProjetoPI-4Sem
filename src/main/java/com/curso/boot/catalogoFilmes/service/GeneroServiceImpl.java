package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.GeneroDao;
import com.curso.boot.catalogoFilmes.domain.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroDao dao;

    @Override
    public void save(Genero genero) {
        dao.save(genero);
    }

    @Override
    public void update(Genero genero) {
        dao.update(genero);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genero findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return dao.findAll();
    }
}
