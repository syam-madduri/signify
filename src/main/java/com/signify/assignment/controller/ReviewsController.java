package com.signify.assignment.controller;


import com.signify.assignment.dto.AverageRatingDTO;
import com.signify.assignment.dto.ReviewDTO;
import com.signify.assignment.entity.Reviews;
import com.signify.assignment.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ReviewsController {


    @Autowired
    ReviewsService reviewsService;

    @GetMapping("/reviews")
    public List<ReviewDTO> reviews(ReviewDTO request) {
        return reviewsService.find(request);
    }

    @PostMapping("/review")
    public Reviews save(@RequestBody Reviews review){
        return reviewsService.save(review);
    }

    @GetMapping("/groupByRatings")
    public Map<Integer, Long> groupByRatings() {
        return reviewsService.groupByRatings();
    }

    @GetMapping("/getAverageRatings")
    public List<AverageRatingDTO> getAverageRatings(){ return reviewsService.getAverageRatings(new ReviewDTO()); }
}
