package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String inicio() {
        return "livros/inicio";
    }

    @GetMapping("/livro")
    public String exibirLivro(Model model) {
        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("978-85-00-00000-0");

        model.addAttribute("livro", livro);

        return "livros/livro";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping()
    public String cadastrarLivro(@ModelAttribute Livro livro) {
        repository.save(livro);
        return "redirect:/livros/lista";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        List<Livro> livros = repository.findAll();
        model.addAttribute("livros", livros);
        return "livros/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/livros/lista";
    }
}