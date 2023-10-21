package com.jorgefernandes.movies.services;

import com.jorgefernandes.movies.domain.movie.Movie;
import com.jorgefernandes.movies.domain.review.Review;
import com.jorgefernandes.movies.repositories.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Review> allReviews() {
        return this.repository.findAll();
    }


    public Review createReview(String reviewBody, String imdbId) {
        Review review = this.repository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewsIds").value(review))
                .first();

        return review;
    }

    public boolean deleteReview(ObjectId reviewId) {
        Review review = this.repository.findById(reviewId).orElse(null);
        if (review != null) {
            this.repository.delete(review);
            return true;
        } else {
            return false; // Review not found
        }
    }
}
