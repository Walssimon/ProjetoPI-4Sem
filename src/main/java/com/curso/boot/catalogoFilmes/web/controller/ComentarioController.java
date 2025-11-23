package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.domain.Comentario;
import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.ComentarioService;
import com.curso.boot.catalogoFilmes.service.AvaliacaoService;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvar/{filmeId}")
    public String salvar(@PathVariable Long filmeId,
                         @RequestParam("texto") String texto,
                       /*  @RequestParam("nota") int nota,*/
                         HttpSession session,
                         RedirectAttributes attr) {

        // 🔹 1 — Carregar filme
        Filme filme = filmeService.findById(filmeId);

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");


        // 🔹 2 — Criar comentário
        Comentario comentario = new Comentario();
        comentario.setFilme(filme);
        comentario.setUsuario(usuario);
        comentario.setTexto(texto);
        comentario.setDataComentario(LocalDate.now());

        comentarioService.save(comentario);

      /*  // 🔹 3 — Criar avaliação associada ao comentário
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setFilme(filme);
        avaliacao.setUsuario(usuario);
        avaliacao.setComentario(comentario);
        avaliacao.setNota((double) nota);

        avaliacaoService.save(avaliacao);*/

        attr.addFlashAttribute("msg", "Comentário enviado!");

        return "redirect:/filme/detalhes/" + filmeId;
    }
}
