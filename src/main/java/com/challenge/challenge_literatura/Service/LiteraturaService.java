package com.challenge.challenge_literatura.Service;


import com.challenge.challenge_literatura.Model.Autor;
import com.challenge.challenge_literatura.Model.GutendexBook;
import com.challenge.challenge_literatura.Model.GutendexResponse;
import com.challenge.challenge_literatura.Model.Libro;

import com.challenge.challenge_literatura.Repository.AutorRepository;
import com.challenge.challenge_literatura.Repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;




import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class LiteraturaService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books";

    public List<Libro> buscarLibrosPorTitulo(String titulo) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", titulo);

        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        GutendexResponse gutendexResponse = objectMapper.readValue(response, GutendexResponse.class);

        return gutendexResponse.getResults().stream().map(this::mapToLibro).collect(Collectors.toList());
    }

    private Libro mapToLibro(GutendexBook gutendexBook) {
        Libro libro = new Libro();
        libro.setTitulo(gutendexBook.getTitle());
        libro.setIdioma(gutendexBook.getFirstLanguage());  // Usa el primer idioma de la lista

        if (!gutendexBook.getAuthors().isEmpty()) {
            Autor autor = new Autor();
            autor.setNombre(gutendexBook.getAuthors().get(0).getName());
            // Guarda el autor en la base de datos y obtiene su ID
            Autor savedAutor = autorRepository.save(autor);
            libro.setAutorId(savedAutor.getId());
        }
        return libro;
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Autor> listarAutoresPorAno(int ano) {
        return autorRepository.findAll().stream()
                .filter(autor -> autor.getAnoNacimiento() == ano)
                .collect(Collectors.toList());
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}