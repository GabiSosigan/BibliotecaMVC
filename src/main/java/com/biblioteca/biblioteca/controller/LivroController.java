package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @GetMapping
    public String inicio(){
        return "livros/inicio";
    }

    @GetMapping("/livro")
    public String exibirLivro(Model model){
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
    public String cadastrarLivro(@ModelAttribute Livro livro, Model model) {
        model.addAttribute("livro", livro);
        return "livros/confirmacao";
    }

    private List livros = new ArrayList<>();

    @PostMapping("/livros")
    public String cadastrar(@ModelAttribute Livro livro) {
        livros.add(livro);
        return "redirect:/livros";
    }

    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        return "livros/lista";
    }
}