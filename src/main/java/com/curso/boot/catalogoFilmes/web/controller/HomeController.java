package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

        @Autowired
        private FilmeService filmeService;

        @GetMapping({"/", "/home"})
        public String home(Model model) {

            // Busca todos os filmes
            model.addAttribute("filmes", filmeService.findAll());
            
            // TODO: Obter ID do usuário logado da sessão
            Long usuarioId = 1L; // Substituir por lógica de sessão
            model.addAttribute("usuarioId", usuarioId);

            // Abre home.html
            return "home";
        }
}
