package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.FilmeAtorDao;
import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class FilmeAtorServiceImpl implements FilmeAtorService {

    @Autowired
    private FilmeAtorDao dao;

    @Override
    public void save(FilmeAtor filmeAtor) { dao.save(filmeAtor); }

    @Override
    public void update(FilmeAtor filmeAtor) { dao.update(filmeAtor); }

    @Override
    public void delete(Long id) { dao.delete(id); }

    @Override
    @Transactional(readOnly = true)
    public FilmeAtor findById(Long id) { return dao.findById(id); }

    @Override
    @Transactional(readOnly = true)
    public List<FilmeAtor> findAll() { return dao.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public List<FilmeAtor> findByFilmeId(Long filmeId) {
        return dao.findByFilmeId(filmeId);
    }
}
