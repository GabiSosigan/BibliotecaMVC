package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String inicio(){
        return "categorias/inicio";
    }

    @GetMapping("/categoria")
    public String exibirCategoria(Model model){
        Categoria categoria = new Categoria();
        categoria.setNome("Terror");
        model.addAttribute("categoria", categoria);
        return "categorias/categoria";
    }

    @GetMapping("/novo")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/cadastro";
    }

    @PostMapping
    public String cadastrarCategoria(@ModelAttribute Categoria categoria) {
        repository.save(categoria);
        return "redirect:/categorias/lista";
    }

    @GetMapping("/lista")
    public String listar (Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "/categorias/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + id));
        model.addAttribute("categoria", categoria);
        return "categorias/cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/categorias/lista";
    }
}