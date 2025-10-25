package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.FavoritoDao;
import com.curso.boot.catalogoFilmes.domain.Favorito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoDao dao;

    @Override
    public void save(Favorito favorito) {
        dao.save(favorito);
    }

    @Override
    public void update(Favorito favorito) {
        dao.update(favorito);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Favorito findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Favorito> findAll() {
        return dao.findAll();
    }
}
