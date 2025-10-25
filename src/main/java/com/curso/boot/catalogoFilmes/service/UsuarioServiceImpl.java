package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.UsuarioDao;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao dao;

    @Override
    public void save(Usuario usuario) {
        dao.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        dao.update(usuario);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return dao.findAll();
    }
}
