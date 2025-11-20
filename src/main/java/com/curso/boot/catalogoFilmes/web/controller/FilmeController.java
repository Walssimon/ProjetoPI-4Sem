package com.curso.boot.catalogoFilmes.web.controller;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/home")
    public String home(Model model) {
        List<Filme> filmes = filmeService.findAll();
        model.addAttribute("filmes", filmeService.findAll());
        return "filme/home"; // Nome do template Thymeleaf
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeService.findById(id);
        model.addAttribute("filme", filme);
        
        // TODO: Obter ID do usuário logado da sessão
        Long usuarioId = 1L; // Substituir por lógica de sessão
        
        // Verificar se o filme está favoritado
        boolean isFavoritado = favoritoService.isFavoritado(usuarioId, id);
        model.addAttribute("isFavoritado", isFavoritado);
        model.addAttribute("usuarioId", usuarioId);
        
        return "filme/details";
    }

}
