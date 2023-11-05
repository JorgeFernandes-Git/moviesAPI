package com.jorgefernandes.movies.services;

import com.jorgefernandes.movies.domain.movie.Movie;
import com.jorgefernandes.movies.dtos.MovieDTO;
import com.jorgefernandes.movies.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> allMovies() {
        return this.repository.findAll();
    }

    public Optional<Movie> singleMovie(String  id) {
        return this.repository.findMovieByImdbId(id);
    }

    public Movie addMovie(MovieDTO data) {
        Movie newMovie = new Movie(data);
        this.repository.save(newMovie);
        return newMovie;
    }
}
