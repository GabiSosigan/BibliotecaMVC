package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @GetMapping
    public String inicio(){
        return "autores/inicio";
    }

    @GetMapping("/autor")
    public String exibirAutor(Model model){
        Autor autor = new Autor();
        autor.setNome("Nora Sakavic");
        autor.setNacionalidade("Estadunidense");

        model.addAttribute("autor", autor);

        return "autores/autor";
    }

    @GetMapping("/novo")
    public String novoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/cadastro";

    }

    @PostMapping()
    public String cadastrarAutor(@ModelAttribute Autor autor, Model model) {
        model.addAttribute("autor", autor);
        return "autores/sucesso";
    }
}
