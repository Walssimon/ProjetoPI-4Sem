package com.curso.boot.demo.web.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping("/details")
    public String Detalhes(){
        return "filme/details";
    }
}
