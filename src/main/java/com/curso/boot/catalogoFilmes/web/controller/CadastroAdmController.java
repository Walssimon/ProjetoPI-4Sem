package com.curso.boot.catalogoFilmes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastroAdm")
public class CadastroAdmController {

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


}
