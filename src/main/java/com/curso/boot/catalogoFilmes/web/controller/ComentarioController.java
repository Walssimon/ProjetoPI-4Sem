package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.domain.Avaliacao;
import com.curso.boot.catalogoFilmes.domain.Comentario;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.AvaliacaoService;
import com.curso.boot.catalogoFilmes.service.ComentarioService;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/salvar/{filmeId}")
    public String salvarComentario(
            @PathVariable Long filmeId,
            @RequestParam("texto") String texto,
            @RequestParam("nota") Double nota
    ) {

        Filme filme = filmeService.findById(filmeId);

        // Criar usuário padrão caso não exista
        Usuario usuario = usuarioService.findById(1L);
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNome("Usuário Padrão");
            usuario.setEmail("padrao@teste.com");
            usuario.setSenha("123");
            usuarioService.save(usuario);
        }

        // Salvar comentário
        Comentario comentario = new Comentario();
        comentario.setFilme(filme);
        comentario.setUsuario(usuario);
        comentario.setTexto(texto);
        comentario.setDataComentario(LocalDate.now());
        comentarioService.save(comentario);

        // Salvar avaliação
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setFilme(filme);
        avaliacao.setUsuario(usuario);
        avaliacao.setComentario(comentario); // <-- ESSENCIAL
        avaliacao.setNota(nota);
        avaliacaoService.save(avaliacao);


        return "redirect:/filme/detalhes/" + filmeId;
    }

}
