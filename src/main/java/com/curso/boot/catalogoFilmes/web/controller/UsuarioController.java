package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.UsuarioDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDaoImpl usuarioDao;

    @GetMapping("/userPage")
    public String UserPage(){
        return "usuario/userPage";
    }

    @GetMapping("/favorite")
    public String FavoritePage(){
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

    @PostMapping("/registro")
    public String RegistrarUser(@ModelAttribute Usuario usuario, RedirectAttributes ra){
        try {
            usuarioDao.save(usuario);
            ra.addFlashAttribute("msgSucesso", "Usuário registrado com sucesso!");
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

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuario/login";
    }
}

