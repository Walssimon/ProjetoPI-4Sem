package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.service.GerarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cadastroAdm")
public class CadastroAdmController {

    @Autowired
    private GerarImagemService gerarImagem;


    @GetMapping("/index")
    public String HomepageAdm(){ return "cadastroAdm/index";}

    @GetMapping("/addActorPage")
    public String AddActorPage(){
        return "cadastroAdm/AddActorPage";
    }

    @GetMapping("/cadNewFilme")
    public String CadNewFilme(){
        return "cadastroAdm/cadNewFilme";
    }

    @GetMapping("/editFilme")
    public String EditFilme(){
        return "cadastroAdm/editFilme";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String uploadImagem(@RequestParam("imagem") MultipartFile imagem) throws Exception {
        gerarImagem.salvarImagem(imagem);
        return "redirect:/cadastroAdm/index";
    }

}
