package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.GeneroFilmeDao;
import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class GeneroFilmeServiceImpl implements GeneroFilmeService {

    @Autowired
    private GeneroFilmeDao dao;

    @Override
    public void save(GeneroFilme generoFilme) {
        dao.save(generoFilme);
    }

    @Override
    public void update(GeneroFilme generoFilme) {
        dao.update(generoFilme);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public GeneroFilme findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneroFilme> findAll() {
        return dao.findAll();
    }


    public List<GeneroFilme> findByFilmeId(Long filmeId) {
        return dao.findByFilmeId(filmeId);
    }

    public void deleteByFilmeId(Long filmeId) {
        dao.deleteByFilmeId(filmeId);
    }

}
