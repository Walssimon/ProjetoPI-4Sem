package com.curso.boot.catalogoFilmes.web.controller;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.GerarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/cadastroAdm")
public class CadastroAdmController {

    @Autowired
    private GerarImagemService gerarImagem;
    @Autowired
    private FilmeService filmeService;


    @GetMapping("/index")
    public String HomepageAdm(Model model){
        model.addAttribute("filmes", filmeService.findAll());
        return "cadastroAdm/index";}

    @GetMapping("/addActorPage")
    public String AddActorPage(){
        return "cadastroAdm/AddActorPage";
    }

    @GetMapping("/cadNewFilme")
    public String CadNewFilme(){
        return "cadastroAdm/cadNewFilme";
    }

    @GetMapping("/editFilme/{id}")
    public String EditFilme(@PathVariable Long id, Model model){
        Filme filme = filmeService.findById(id);
        model.addAttribute("filme", filme);
        return "cadastroAdm/editFilme";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadImagem(@RequestParam("imagem") MultipartFile imagem) throws Exception {
        gerarImagem.salvarImagem(imagem);
        return "redirect:/cadastroAdm/index";
    }

}
