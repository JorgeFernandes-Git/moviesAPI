package com.jorgefernandes.movies.controllers;

import com.jorgefernandes.movies.domain.review.Review;
import com.jorgefernandes.movies.repositories.ReviewRepository;
import com.jorgefernandes.movies.services.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository repository;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReview(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        if (id != null) {
            Optional<Review> review = repository.findById(new ObjectId(id));
            if (review.isPresent()) {
                repository.deleteById(new ObjectId(id));
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}