package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.GeneroDao;
import com.curso.boot.catalogoFilmes.dao.GeneroFilmeDao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.strategy.BuscarPorGeneroStrategy;
import com.curso.boot.catalogoFilmes.strategy.FilmeSearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors; // Importar

@Controller
public class HomeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private FilmeSearchContext buscafilmesContext;

    @Autowired
    private GeneroDao generoDao;

    @Autowired
    private GeneroFilmeDao generoFilmeDao;

    @GetMapping({"/", "/home"})
    public String home(
            @RequestParam(required = false) String genero,
            Model model,
            HttpSession session) {

        // Verifica login
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }

        Long usuarioId = usuarioLogado.getId();
        model.addAttribute("usuarioId", usuarioId);

        // Lógica para obter filmes de destaque para o carrossel
        // Exemplo: pegar os 5 primeiros filmes
        List<Filme> todosOsFilmes = filmeService.findAll();
        List<Filme> filmesDestaque = todosOsFilmes.stream()
                .limit(5) // Pega os 5 primeiros
                .collect(Collectors.toList());
        model.addAttribute("filmesDestaque", filmesDestaque); // Adiciona ao modelo


        // Lógica de busca por gênero (já existente)
        if (genero != null && !genero.isEmpty()) {
            BuscarPorGeneroStrategy estrategia = new BuscarPorGeneroStrategy(
                    genero,
                    generoDao,
                    generoFilmeDao
            );
            buscafilmesContext.setStrategy(estrategia);
            List<Filme> filmes = buscafilmesContext.buscarFilmes();

            model.addAttribute("filmes", filmes);
            model.addAttribute("generoSelecionado", genero);
        } else {
            // Sem filtro → lista todos
            model.addAttribute("filmes", todosOsFilmes); // Usar a lista completa se não houver filtro
        }

        return "home";
    }
}