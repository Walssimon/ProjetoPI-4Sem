
package com.curso.boot.catalogoFilmes.web.controller;
import com.curso.boot.catalogoFilmes.dao.AtorDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Ator;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import com.curso.boot.catalogoFilmes.service.FilmeService;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.service.GerarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

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
    public String CadNewFilme(){
        return "cadastroAdm/cadNewFilme";
    }

    @GetMapping("/editFilme/{id}")
    public String EditFilme(@PathVariable Long id, Model model){
        Filme filme = filmeService.findById(id);
        model.addAttribute("filme", filme);

        System.out.println("===== GENEROS DO FILME =====");
        if (filme.getGeneros() == null) {
            System.out.println("filme.generos é NULL");
        } else if (filme.getGeneros().isEmpty()) {
            System.out.println("filme.generos está VAZIO");
        } else {
            filme.getGeneros().forEach(g -> {
                System.out.println("Genero ID: " + g.getGenero().getId()
                        + " Nome: " + g.getGenero().getNomeGenero());
            });
        }
        System.out.println("============================");

        return "cadastroAdm/editFilme";
    }


    @PostMapping("/editFilme/{id}")
    public String salvarEdicaoFilme(
            @PathVariable Long id,
            @RequestParam("nomeFilme") String nomeFilme,
            @RequestParam("descricao") String descricao,
            @RequestParam("duracao") Integer duracao,
            @RequestParam("dataLancamento") String dataLancamento,
            @RequestParam("avaliacao") Double avaliacao,
            @RequestParam("classificacaoIndicativa") Integer classificacaoIndicativa,
            @RequestParam(value = "dados", required = false) MultipartFile dados,
            @RequestParam(value = "dados_banner", required = false) MultipartFile dados_banner
    ) {
        Filme filme = filmeService.findById(id);

        if (filme == null) {
            return "redirect:/cadastroAdm/index";
        }

        filme.setNomeFilme(nomeFilme);
        filme.setDescricao(descricao);
        filme.setDuracao(duracao);
        filme.setAvaliacao(avaliacao);
        filme.setClassificacaoIndicativa(classificacaoIndicativa);

        if (dataLancamento != null && !dataLancamento.isEmpty()) {
            try {
                filme.setDataLancamento(LocalDate.parse(dataLancamento));
            } catch (Exception e) {
                System.out.println("Erro ao converter data: " + dataLancamento);
            }
        }

        // imagens
        try {
            if (dados != null && !dados.isEmpty()) {
                filme.setDados(dados.getBytes());
            }
            if (dados_banner != null && !dados_banner.isEmpty()) {
                filme.setDadosBanner(dados_banner.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filmeService.salvar(filme);

        return "redirect:/cadastroAdm/index";
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

}
