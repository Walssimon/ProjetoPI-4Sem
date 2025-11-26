package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.GeneroDao;
import com.curso.boot.catalogoFilmes.dao.GeneroFilmeDao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.strategy.BuscarPorGeneroStrategy;
import com.curso.boot.catalogoFilmes.strategy.FilmeSearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping({"/", "/home"})
    public String home(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String nome,
            Model model,
            HttpSession session) {

        // Verifica login
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }

        Long usuarioId = usuarioLogado.getId();
        model.addAttribute("usuarioId", usuarioId);
        List<Long> favoritosIds = favoritoService.buscarIdsFavoritos(usuarioId);
        model.addAttribute("favoritosIds", favoritosIds);


        // 🔥 Sempre carregar filmes de destaque
        List<Filme> filmesDestaque = filmeService.buscarAleatorios(5);
        model.addAttribute("filmesDestaque", filmesDestaque);

        // 🔎 Busca por nome
        if (nome != null && !nome.isEmpty()) {
            model.addAttribute("filmes", filmeService.buscarPorNome(nome));
            model.addAttribute("nomeBuscado", nome);
            return "home";
        }


        // Lógica original de listagem
        List<Filme> todosOsFilmes = filmeService.findAll();

        // 🔎 Busca por gênero
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
            model.addAttribute("filmes", todosOsFilmes);
        }

        return "home";
    }



}