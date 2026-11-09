package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Categoria;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private List<Categoria> categorias = new ArrayList<>();

    @GetMapping
    public String inicio(){
        return "categorias/inicio";
    }

    @GetMapping("/categoria")
    public String exibirCategoria(Model model){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Terror");
        model.addAttribute("categoria", categoria);

        return "categorias/categoria";
    }

    @GetMapping("/novo")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/cadastro";

    }

    @PostMapping()
    public String cadastrarCategoria(@ModelAttribute Categoria categoria, Model model) {
        categorias.add(categoria);
        return "redirect:/categorias/lista";
    }

    @GetMapping("/lista")
    public String listar (Model model) {
        model.addAttribute("categorias", categorias);
        return "categorias/lista";
    }

    @GetMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice){
        if (indice >= 0 && indice < categorias.size()) {
            categorias.remove(indice);
        }
        return "redirect:/categorias/lista";
    }
}

