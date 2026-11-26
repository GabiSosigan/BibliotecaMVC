package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository repository;

    public AutorController(AutorRepository repository) {
        this.repository = repository;
    }

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

    @PostMapping
    public String cadastrarAutor(@ModelAttribute Autor autor) {
        repository.save(autor);
        return "redirect:/autores/lista";
    }

    @GetMapping("/lista")
    public String listar (Model model) {
        model.addAttribute("autores", repository.findAll());
        return "autores/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado: " + id));
        model.addAttribute("autor", autor);
        return "autores/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/autores/lista";
    }
}