package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.FavoritoDao;
import com.curso.boot.catalogoFilmes.domain.Favorito;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoDao dao;
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private UsuarioService usuarioService;

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
    
    @Override
    public void favoritarFilme(Long usuarioId, Long filmeId) {
        // Verificar se já está favoritado
        if (dao.existsByUsuarioAndFilme(usuarioId, filmeId)) {
            throw new RuntimeException("Filme já está nos favoritos");
        }
        
        // Buscar usuário e filme
        Usuario usuario = usuarioService.findById(usuarioId);
        Filme filme = filmeService.findById(filmeId);
        
        if (usuario == null || filme == null) {
            throw new RuntimeException("Usuário ou filme não encontrado");
        }
        
        // Criar novo favorito
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setFilme(filme);
        
        dao.save(favorito);
    }
    
    @Override
    public void removerFavorito(Long usuarioId, Long filmeId) {
        Favorito favorito = dao.findByUsuarioAndFilme(usuarioId, filmeId);
        if (favorito != null) {
            dao.delete(favorito.getId());
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Favorito> buscarFavoritosPorUsuario(Long usuarioId) {
        return dao.findByUsuarioId(usuarioId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isFavoritado(Long usuarioId, Long filmeId) {
        return dao.existsByUsuarioAndFilme(usuarioId, filmeId);
    }
}
