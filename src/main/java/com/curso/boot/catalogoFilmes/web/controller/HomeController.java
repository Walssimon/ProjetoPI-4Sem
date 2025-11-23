package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.fasterxml.jackson.databind.DatabindContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping({"/", "/home"})
    public String home(Model model, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if ( usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
        Long usuarioId = usuarioLogado != null ? usuarioLogado.getId() : null;
        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("filmes", filmeService.findAll());
        // Abre home.html
        return "home";
    }
}
