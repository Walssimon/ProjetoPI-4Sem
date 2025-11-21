package com.curso.boot.catalogoFilmes.web.controller;

import com.curso.boot.catalogoFilmes.dao.UsuarioDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String RegistrarUser(@ModelAttribute Usuario usuario, @RequestParam("rpsenha") String repetirSenha){
        usuarioDao.save(usuario);
        return "redirect:/usuario/login";
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
}

