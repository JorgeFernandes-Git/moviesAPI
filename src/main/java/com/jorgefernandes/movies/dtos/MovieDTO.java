package com.jorgefernandes.movies.dtos;

import java.util.List;

public record MovieDTO(String imdbId,
                       String title,
                       String releaseDate,
                       String trailerLink,
                       String poster,
                       List<String> genres,
                       List<String> backdrops) {
}
