
package com.curso.boot.catalogoFilmes.web.controller;
import com.curso.boot.catalogoFilmes.dao.AtorDaoImpl;
import com.curso.boot.catalogoFilmes.domain.*;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.GeneroFilmeService;
import com.curso.boot.catalogoFilmes.service.GeneroService;
import com.curso.boot.catalogoFilmes.service.GerarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cadastroAdm")
public class CadastroAdmController {

    @Autowired
    private GerarImagemService gerarImagem;
    @Autowired
    private FilmeService filmeService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private GeneroFilmeService generoFilmeService;

    @Autowired
    private AtorDaoImpl atorDao;


    @GetMapping("/index")
    public String HomepageAdm(Model model){
        model.addAttribute("filmes", filmeService.findAll());
        return "cadastroAdm/index";}

    @GetMapping("/addActorPage")
    public String AddActorPage(){
        return "cadastroAdm/AddActorPage";
    }

    @GetMapping("/cadNewFilme")
    public String CadNewFilme(Model model){
        model.addAttribute("generos", generoService.findAll());
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

    @PostMapping("/addActor")
    public String addActor(@ModelAttribute Ator ator) {
        atorDao.save(ator);
        return "redirect:/cadastroAdm/addActorPage";
    }

    @PostMapping("/cadNewFilme")
    public String salvarFilme(@RequestParam String nomeFilme,
                              @RequestParam String descricao,
                              @RequestParam int duracao,
                              @RequestParam String dataLancamento,
                              @RequestParam Double avaliacao,
                              @RequestParam Integer classificacaoIndicativa,
                              @RequestParam Long generoId,
                              @RequestParam("imagem") MultipartFile imagem,
                              @RequestParam("banner") MultipartFile banner) throws IOException {

        Filme filme = new Filme();
        filme.setNomeFilme(nomeFilme);
        filme.setDescricao(descricao);
        filme.setDuracao(duracao);
        filme.setDataLancamento(LocalDate.parse(dataLancamento));
        filme.setAvaliacao(avaliacao);
        filme.setClassificacaoIndicativa(classificacaoIndicativa);

        filme.setDados(imagem.getBytes());
        filme.setDadosBanner(banner.getBytes());

        filmeService.save(filme);

        // --- cadastrar na genero_filme ---
        Genero genero = generoService.findById(generoId);

        GeneroFilme gf = new GeneroFilme();
        gf.setFilme(filme);
        gf.setGenero(genero);

        generoFilmeService.save(gf);

        return "redirect:/cadastroAdm/index";
    }
}
