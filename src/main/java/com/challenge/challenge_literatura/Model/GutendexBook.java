package com.challenge.challenge_literatura.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBook {
    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;  // Cambia esto para que sea una lista de Strings

    // Si aún quieres tener una sola string para almacenar el primer idioma
    public String getFirstLanguage() {
        return (languages != null && !languages.isEmpty()) ? languages.get(0) : null;
    }
}