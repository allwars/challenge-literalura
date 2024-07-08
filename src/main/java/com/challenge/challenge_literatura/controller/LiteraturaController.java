package com.challenge.challenge_literatura.controller;


import com.challenge.challenge_literatura.Model.Autor;
import com.challenge.challenge_literatura.Model.Libro;
import com.challenge.challenge_literatura.Service.LiteraturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/literatura")
public class LiteraturaController {

    @Autowired
    private LiteraturaService literaturaService;

    @GetMapping("/buscar-libros")
    public List<Libro> buscarLibrosPorTitulo(@RequestParam String titulo) throws IOException {
        return literaturaService.buscarLibrosPorTitulo(titulo);
    }

    @GetMapping("/listar-autores")
    public List<Autor> listarAutoresRegistrados() {
        return literaturaService.listarAutoresRegistrados();
    }

    @GetMapping("/listar-libros")
    public List<Libro> listarLibrosRegistrados() {
        return literaturaService.listarLibrosRegistrados();
    }

    @GetMapping("/listar-autores-por-ano")
    public List<Autor> listarAutoresPorAno(@RequestParam int ano) {
        return literaturaService.listarAutoresPorAno(ano);
    }

    @GetMapping("/listar-libros-por-idioma")
    public List<Libro> listarLibrosPorIdioma(@RequestParam String idioma) {
        return literaturaService.listarLibrosPorIdioma(idioma);
    }
}