package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Comentario;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import com.curso.boot.catalogoFilmes.service.AvaliacaoService;
import com.curso.boot.catalogoFilmes.service.ComentarioService;
import com.curso.boot.catalogoFilmes.service.FilmeAtorService;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private FilmeAtorService filmeAtorService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("filmes", filmeService.findAll());
        return "filme/home";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {

        Filme filme = filmeService.findById(id);
        // BUSCAR APENAS AVALIAÇÕES DESSE FILME
        // Comentários filtrados
        List<Comentario> comentarios = comentarioService.findByFilmeId(id);


        Map<Long, Avaliacao> avaliacoesPorComentario = new HashMap<>();

        for (Comentario c : comentarios) {
            Avaliacao av = avaliacaoService.findByComentarioId(c.getId());
            avaliacoesPorComentario.put(c.getId(), av);
        }

        // 🔥 atores do filme
        List<FilmeAtor> atores = filmeAtorService.findByFilmeId(id);
        model.addAttribute("atores", atores);

        model.addAttribute("comentarios", comentarios);
        model.addAttribute("avaliacoesPorComentario", avaliacoesPorComentario);
        model.addAttribute("filme", filme);
        model.addAttribute("avaliacoes", avaliacaoService.findByFilmeId(id));


        return "filme/details";
    }
}
