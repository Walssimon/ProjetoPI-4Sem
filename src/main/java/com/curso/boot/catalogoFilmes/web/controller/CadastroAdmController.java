
package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.AtorDaoImpl;
import com.curso.boot.catalogoFilmes.domain.*;
import com.curso.boot.catalogoFilmes.service.*;
import com.curso.boot.catalogoFilmes.domain.Filme;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private FilmeAtorService filmeAtorService;
    @Autowired
    private AtorService atorService;


    @GetMapping("/index")
    public String HomepageAdm(Model model,
                              HttpSession session) {

        // Verifica login
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
        model.addAttribute("filmes", filmeService.findAll());
        return "cadastroAdm/index";
    }

    @PostMapping("/addActor")
    public String addActor(
            @RequestParam("nomeAtor") String nomeAtor,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam("imagem") MultipartFile imagem,
            HttpSession session) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }

        Ator ator = new Ator();
        ator.setNomeAtor(nomeAtor);
        ator.setDataNascimento(LocalDate.parse(dataNascimento));

        try {
            if (!imagem.isEmpty()) {
                ator.setDados(imagem.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        atorDao.save(ator);

        return "redirect:/cadastroAdm/addActorPage";
    }

    @GetMapping("/addActorPage")
    public String AddActorPage(Model model, HttpSession session) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }

        model.addAttribute("listaAtores", atorDao.findAll());
        return "cadastroAdm/addActorPage";
    }

    @PostMapping("/deleteFilme/{id}")
    public String deleteFilme(@PathVariable Long id) {

        filmeAtorService.deleteByFilmeId(id);
        generoFilmeService.deleteByFilmeId(id);
        filmeService.delete(id);

        return "redirect:/cadastroAdm/index";
    }




    @GetMapping("/cadNewFilme")
    public String CadNewFilme(Model model,
                              HttpSession session) {

        // Verifica login
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
        model.addAttribute("generos", generoService.findAll());
        return "cadastroAdm/cadNewFilme";
    }

    @GetMapping("/editFilme/{id}")
    public String EditFilme(@PathVariable Long id, Model model,
                            HttpSession session) {


        // Verifica login
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
        Filme filme = filmeService.findById(id);
        model.addAttribute("filme", filme);
        List<Ator> listaAtores = atorService.findAll();
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
        List<FilmeAtor> atoresDoFilme = filmeAtorService.findByFilmeId(id);

        System.out.println("===== ATORES DO FILME =====");
        if (atoresDoFilme == null) {
            System.out.println("RETORNOU NULL");
        } else if (atoresDoFilme.isEmpty()) {
            System.out.println("LISTA VAZIA");
        } else {
            atoresDoFilme.forEach(a -> {
                System.out.println(
                        "Ator: " + a.getAtor().getNomeAtor() +
                                " | Papel: " + a.getPapel()
                );
            });
        }
        System.out.println("============================");

        model.addAttribute("atoresDoFilme", atoresDoFilme);
        model.addAttribute("filme", filme);
        model.addAttribute("atores", atorDao.findAll());
        model.addAttribute("atoresDoFilme", filmeAtorService.findByFilmeId(id));
        model.addAttribute("listaAtores", listaAtores);

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
            @RequestParam(value = "dados_banner", required = false) MultipartFile dados_banner,
            Model model,
            HttpSession session
    ) {
        Filme filme = filmeService.findById(id);




        if (filme == null) {
            return "redirect:/cadastroAdm/index";
        }
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
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

    @PostMapping("/addAtorFilme")
    @ResponseBody
    public ResponseEntity<?> addAtorFilme(
            @RequestParam Long filmeId,
            @RequestParam Long atorId,
            @RequestParam String papel
    ) {
        try {
            FilmeAtor fa = new FilmeAtor();
            fa.setFilme(filmeService.findById(filmeId));
            fa.setAtor(atorService.findById(atorId));
            fa.setPapel(papel);

            filmeAtorService.save(fa);

            return ResponseEntity.ok("Ator adicionado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }


    @GetMapping("/listaAtoresFilme/{id}")
    public String listaAtoresFilme(@PathVariable Long id, Model model) {
        List<FilmeAtor> atoresDoFilme = filmeAtorService.findByFilmeId(id);
        model.addAttribute("atoresDoFilme", atoresDoFilme);
        return "fragments/layoutAtores :: layout";
    }
    @GetMapping("/listAtores")
    public String listarAtores(Model model) {
        model.addAttribute("listaAtores", atorService.findAll());
        return "cadastroAdm/listaAtores";
    }




    @PostMapping("/upload")
    @ResponseBody
    public String uploadImagem(@RequestParam("imagem") MultipartFile imagem) throws Exception {
        gerarImagem.salvarImagem(imagem);
        return "redirect:/cadastroAdm/index";
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
