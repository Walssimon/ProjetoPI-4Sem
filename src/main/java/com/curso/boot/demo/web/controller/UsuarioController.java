package com.curso.boot.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

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
}

