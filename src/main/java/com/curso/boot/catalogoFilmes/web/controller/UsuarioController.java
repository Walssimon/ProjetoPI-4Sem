package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.UsuarioDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Favorito;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDaoImpl usuarioDao;

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/userPage")
    public String UserPage(){
        return "usuario/userPage";
    }


    @GetMapping("/favorite")
    public String FavoritePage(Model model,  HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
        Long usuarioId = usuarioLogado.getId();
        List<Favorito> favoritos = favoritoService.buscarFavoritosPorUsuario(usuarioId);
        model.addAttribute("favoritos", favoritos);
        return "usuario/favorite";
    }

    @GetMapping("/login")
    public String Login(){
        return "usuario/login";
    }

    @PostMapping("/registro")
    public String RegistrarUser(@ModelAttribute Usuario usuario, @RequestParam("rpsenha") String repetirSenha){
        usuarioDao.save(usuario);
        return "redirect:/usuario/login";
    }
    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "usuario/registro";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session
    ) {

        Usuario usuario = usuarioDao.findByEmailAndSenha(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
//            Usuario usuarioteste = (Usuario) session.getAttribute("usuarioLogado");
//            String nome = usuarioteste.getNome();
//
//            System.out.println("Nome do usuário logado: " + nome);
            return "redirect:/home";
        }

        return "redirect:/usuario/login";
    }
    
    // Endpoint para adicionar favorito (AJAX)
    @PostMapping("/favoritar")
    @ResponseBody
    public ResponseEntity<String> favoritarFilme(
            @RequestParam Long filmeId,
            @RequestParam Long usuarioId) {
        try {
            favoritoService.favoritarFilme(usuarioId, filmeId);
            return ResponseEntity.ok("Filme adicionado aos favoritos");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Endpoint para remover favorito (AJAX)
    @PostMapping("/remover-favorito")
    @ResponseBody
    public ResponseEntity<String> removerFavorito(
            @RequestParam Long filmeId,
            @RequestParam Long usuarioId) {
        try {
            favoritoService.removerFavorito(usuarioId, filmeId);
            return ResponseEntity.ok("Filme removido dos favoritos");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Endpoint para verificar se está favoritado
    @GetMapping("/verificar-favorito")
    @ResponseBody
    public ResponseEntity<Boolean> verificarFavorito(
            @RequestParam Long filmeId,
            @RequestParam Long usuarioId) {
        boolean favoritado = favoritoService.isFavoritado(usuarioId, filmeId);
        return ResponseEntity.ok(favoritado);
    }
}

