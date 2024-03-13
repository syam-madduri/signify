package com.signify.assignment.controller;


import com.signify.assignment.dto.AverageRatingDTO;
import com.signify.assignment.dto.ReviewDTO;
import com.signify.assignment.entity.Review;
import com.signify.assignment.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ReviewController {


    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public List<ReviewDTO> reviews(ReviewDTO request) {
        return reviewService.find(request);
    }

    @PostMapping("/review")
    public Review save(@RequestBody Review review){
        return reviewService.save(review);
    }

    @GetMapping("/groupByRatings")
    public Map<Integer, Long> groupByRatings() {
        return reviewService.groupByRatings();
    }

    @GetMapping("/getAverageRatings")
    public List<AverageRatingDTO> getAverageRatings(){ return reviewService.getAverageRatings(new ReviewDTO()); }
}
