package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.domain.Favorito;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
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
    private FavoritoService favoritoService;

    @GetMapping("/userPage")
    public String UserPage(){
        return "usuario/userPage";
    }

    @GetMapping("/favorite")
    public String FavoritePage(Model model) {
        // TODO: Obter ID do usuário logado da sessão
        // Por enquanto, usar um ID fixo para testes (ex: 1L)
        Long usuarioId = 1L; // Substituir por lógica de sessão
        
        List<Favorito> favoritos = favoritoService.buscarFavoritosPorUsuario(usuarioId);
        model.addAttribute("favoritos", favoritos);
        
        return "usuario/favorite";
    }

    @GetMapping("/login")
    public String Login(){
        return "usuario/login";
    }

    @GetMapping("/registro")
    public String Registrar(){
        return "usuario/registro";
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

