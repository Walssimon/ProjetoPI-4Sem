package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.FilmeDao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Imagem;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class FilmeServiceImpl implements FilmeService {


    @Autowired
    private FilmeDao dao;

    @Override
    public void save(Filme filme) {
        dao.save(filme);
    }

    @Override
    public void update(Filme filme) {
        dao.update(filme);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Filme findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Filme> findAll() {
        return dao.findAll();
    }

    @Override
    public void salvar(Filme filme) {
        if (filme.getId() == null) {
            dao.save(filme);
        } else {
            dao.update(filme);
        }
    }

    @Override
    public List<Filme> buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    @Override
    public List<Filme> buscarAleatorios(int quantidade) {
        return dao.buscarAleatorios(quantidade);
    }

}
