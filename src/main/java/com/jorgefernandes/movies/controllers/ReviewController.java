package com.jorgefernandes.movies.controllers;
import com.jorgefernandes.movies.domain.review.Review;
import com.jorgefernandes.movies.services.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
//        return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"), payload.get("userId")), HttpStatus.CREATED);
        String reviewBody = payload.get("reviewBody");
        String imdbId = payload.get("imdbId");
        String userIdStr = payload.get("userId");
        String userNickname = payload.get("userNickname");

        if (reviewBody == null || imdbId == null || userIdStr == null || userNickname == null) {
            // Handle missing data, such as returning a bad request response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ObjectId userId = new ObjectId(userIdStr); // Convert userId string to ObjectId

        Review createdReview = reviewService.createReview(reviewBody, imdbId, userId, userNickname);

        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.allReviews(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable ObjectId id) {
//        ObjectId reviewId = payload.get("id");

        if (reviewService.deleteReview(id)) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found or couldn't be deleted", HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping
//    public ResponseEntity<String> deleteReview(@RequestBody Map<String, ObjectId> payload) {
//        ObjectId reviewId = payload.get("id");
//
//        if (reviewService.deleteReview(reviewId)) {
//            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Review not found or couldn't be deleted", HttpStatus.NOT_FOUND);
//        }
//    }

}