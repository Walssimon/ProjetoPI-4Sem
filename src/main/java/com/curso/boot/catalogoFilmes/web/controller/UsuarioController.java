package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.UsuarioDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Favorito;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import com.curso.boot.catalogoFilmes.service.FavoritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDaoImpl usuarioDao;

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/userPage")
    public String UserPage(Model model,  HttpSession session){
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login";
        }
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
    public String RegistrarUser(@ModelAttribute Usuario usuario, RedirectAttributes ra){
        try {
            usuarioDao.save(usuario);
            return "redirect:/usuario/login";

        } catch (DataIntegrityViolationException dive) {
            // Erro comum: chave única, email duplicado, etc.
            ra.addFlashAttribute("msgErro", "Já existe um usuário com esse email.");
            return "redirect:/usuario/registro";

        } catch (Exception e) {
            // Qualquer outro erro do banco
            ra.addFlashAttribute("msgErro", "Erro ao registrar usuário. Tente novamente.");
            return "redirect:/usuario/registro";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "usuario/registro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Finaliza a sessão
        return "redirect:/usuario/login"; // Volta para o login
    }
    @PostMapping("/logout")
    public String logoutForm(HttpSession session) {
        session.invalidate(); // Finaliza a sessão
        return "redirect:/usuario/login"; // Volta para o login
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

            return "redirect:/home";
        }

        return "redirect:/usuario/login";
    }

    @PostMapping("/editUserImage/{id}")
    public String editIImage(
            @PathVariable Long id,
            @RequestParam(value = "dados", required = false) MultipartFile dados,
            HttpSession session
    ) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {

            Usuario editedUser = usuarioDao.findById(id);

            try{
                if (dados != null && !dados.isEmpty()) {
                    editedUser.setDados(dados.getBytes());
                }
            } catch (Exception e) {
               e.printStackTrace();
            }

            usuarioDao.save(editedUser);

            session.setAttribute("usuarioLogado", editedUser);

            return "redirect:/usuario/userPage";
        }

        return "redirect:/usuario/userPage";
    }

    @PostMapping("/editUserName/{id}")
    public String editName(
            @PathVariable Long id,
            @RequestParam String nome,
            HttpSession session
    ) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {

            Usuario editedUser = usuarioDao.findById(id);

            editedUser.setNome(nome);

            usuarioDao.save(editedUser);

            session.setAttribute("usuarioLogado", editedUser);

            return "redirect:/usuario/userPage";
        }

        return "redirect:/usuario/userPage";
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

