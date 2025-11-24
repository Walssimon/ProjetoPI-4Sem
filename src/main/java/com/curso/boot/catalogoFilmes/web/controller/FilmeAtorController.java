package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.AtorDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Ator;
import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import com.curso.boot.catalogoFilmes.service.FilmeAtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ator")
public class FilmeAtorController {

    @Autowired
    private FilmeAtorService filmeAtorService;

    @Autowired
    private AtorDaoImpl atorDao;

    @GetMapping("/lista")
    public String listarAtores(Model model) {
        List<Ator> atores = atorDao.findAll();
        model.addAttribute("atores", atores);
        return "cadastroAdm/listaAtores"; // você cria essa página OU usa fragmentos
    }

    @GetMapping("/porFilme/{filmeId}")
    @ResponseBody
    public List<FilmeAtor> listarAtoresDoFilme(@PathVariable Long filmeId) {
        return filmeAtorService.findByFilmeId(filmeId);
    }
}