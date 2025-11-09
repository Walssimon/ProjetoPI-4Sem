package com.curso.boot.catalogoFilmes.service;

import org.springframework.web.multipart.MultipartFile;

public interface GerarImagemService {
    void salvarImagem(MultipartFile arquivo) throws Exception;
}
