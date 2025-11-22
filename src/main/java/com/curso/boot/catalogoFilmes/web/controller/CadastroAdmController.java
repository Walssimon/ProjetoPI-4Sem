package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.AtorDaoImpl;
import com.curso.boot.catalogoFilmes.dao.FilmeAtorDao;
import com.curso.boot.catalogoFilmes.dao.FilmeDao;
import com.curso.boot.catalogoFilmes.domain.Ator;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.FilmeAtor;
import com.curso.boot.catalogoFilmes.service.GerarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/cadastroAdm")
public class CadastroAdmController {

    @Autowired
    private GerarImagemService gerarImagem;

    @Autowired
    private AtorDaoImpl atorDao;

    @Autowired
    private FilmeAtorDao filmeAtorDao;

    @Autowired
    private FilmeDao filmeDao;

    @GetMapping("/index")
    public String HomepageAdm() {
        return "cadastroAdm/index";
    }

    @GetMapping("/addActorPage")
    public String AddActorPage() {
        return "cadastroAdm/addActorPage";
    }

    @GetMapping("/cadNewFilme")
    public String CadNewFilme(Model model) {
        List<Ator> atores = atorDao.findAll();
        model.addAttribute("atores", atores);
        return "cadastroAdm/cadNewFilme";
    }

    @GetMapping("/editFilme")
    public String EditFilme() {
        return "cadastroAdm/editFilme";
    }

    @PostMapping("/upload")
    public String uploadImagem(@RequestParam("imagem") MultipartFile imagem) throws Exception {
        gerarImagem.salvarImagem(imagem);
        return "redirect:/cadastroAdm/index";
    }


    @PostMapping("/addActor")
    public String addActor(@ModelAttribute Ator ator) {
        atorDao.save(ator);
        return "redirect:/cadastroAdm/addActorPage";
    }

    @PostMapping("/addFilmeAtor")
    public String addFilmeAtor(
            @RequestParam("idFilme") Long idFilme,
            @RequestParam("idAtor") Long idAtor,
            @RequestParam(value = "papel", required = false) String papel
    ) {
        Filme filme = filmeDao.findById(idFilme);
        Ator ator = atorDao.findById(idAtor);

        if (filme == null || ator == null) {
            throw new RuntimeException("Filme ou Ator não encontrado!");
        }

        FilmeAtor filmeAtor = new FilmeAtor();
        filmeAtor.setFilme(filme);
        filmeAtor.setAtor(ator);
        filmeAtor.setPapel(papel);

        filmeAtorDao.save(filmeAtor);

        return "redirect:/cadastroAdm/cadNewFilme";

    }
}
