package com.curso.boot.catalogoFilmes.web.controller;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.FilmeService;
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

    @GetMapping("/home")
    public String home(Model model) {
        List<Filme> filmes = filmeService.findAll();
        model.addAttribute("filmes", filmes);
        return "filme/home"; // Nome do template Thymeleaf
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeService.findById(id);
        model.addAttribute("filme", filme);
        return "filme/details";
    }

}
