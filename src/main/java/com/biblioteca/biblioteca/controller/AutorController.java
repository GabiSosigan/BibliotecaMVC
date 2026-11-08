package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private List<Autor> autores = new ArrayList<>();

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
        autores.add(autor);
        return "redirect:/autores/lista";
    }

    @GetMapping("/lista")
    public String listar (Model model) {
        model.addAttribute("autores", autores);
        return "autores/lista";
    }

    @GetMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice){
        if (indice >= 0 && indice < autores.size()) {
            autores.remove(indice);
        }
        return "redirect:/autores/lista";
    }
}
