package com.jorgefernandes.movies.domain.movie;

import com.jorgefernandes.movies.domain.review.Review;
import com.jorgefernandes.movies.dtos.MovieDTO;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private ObjectId id;

    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;

    @DocumentReference
    private List<Review> reviewsIds;

    public Movie(MovieDTO data) {
        this.imdbId = data.imdbId();
        this.title = data.title();
        this.releaseDate = data.releaseDate();
        this.trailerLink = data.trailerLink();
        this.poster = data.poster();
        this.genres = data.genres();
        this.backdrops = data.backdrops();
    }
}
