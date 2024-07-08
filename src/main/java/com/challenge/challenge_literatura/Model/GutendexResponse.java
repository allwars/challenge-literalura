package com.challenge.challenge_literatura.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<GutendexBook> results;
}

