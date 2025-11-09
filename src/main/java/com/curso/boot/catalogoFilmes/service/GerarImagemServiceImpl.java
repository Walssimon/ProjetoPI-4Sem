package com.curso.boot.catalogoFilmes.service;

import com.curso.boot.catalogoFilmes.dao.ImagemDao;
import com.curso.boot.catalogoFilmes.domain.Imagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class GerarImagemServiceImpl implements GerarImagemService {

    @Autowired
    private ImagemDao dao;

    @Override
    public void salvarImagem(MultipartFile arquivo) throws Exception {
        Imagem img = new Imagem();
        img.setNome(arquivo.getOriginalFilename());
        img.setTipo(arquivo.getContentType());
        img.setDados(arquivo.getBytes());
        System.out.println("Salvando imagem: " + arquivo.getOriginalFilename());
        dao.save(img);
    }
}
