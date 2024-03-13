package com.signify.assignment.service;

import com.signify.assignment.dto.AverageRatingDTO;
import com.signify.assignment.dto.ReviewDTO;
import com.signify.assignment.entity.Review;
import com.signify.assignment.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    public List<ReviewDTO> find(ReviewDTO request) {
        return reviewRepository.findReviews(request.getRating(), request.getReviewed_date(), request.getReview_source());
    }

    public Map<Integer, Long> groupByRatings() {
        return reviewRepository.findAll().stream().collect(groupingBy(Review::getRating, Collectors.counting()));
    }

    public Review save(Review review) {
        if(Optional.ofNullable(review.getReviewed_date()).isEmpty()){
            review.setReviewed_date(new Date());
        }
        return reviewRepository.save(review);
    }
    public void save(List<Review> users) {
        reviewRepository.saveAll(users);
    }

    public List<AverageRatingDTO> getAverageRatings(ReviewDTO request) {
        List<AverageRatingDTO> response = new ArrayList<>();
        List<ReviewDTO> list = reviewRepository.findReviews(request.getRating(), request.getReviewed_date(), request.getReview_source());
        list.forEach(review -> review.setReviewed_date(review.getReviewed_date().toString().split(" ")[0]));
        Map<Object, Map<String, List<ReviewDTO>>> tempMap = list.stream().collect(groupingBy(ReviewDTO:: getReviewed_date, groupingBy(ReviewDTO:: getReview_source)));
        tempMap.forEach((k1, v1) -> v1.forEach((k2, v2) ->{
            AverageRatingDTO res = new AverageRatingDTO();
            int totalSum = v2.stream().mapToInt(ReviewDTO::getRating).sum();
            res.setReviewed_date(k1.toString());
            res.setReview_source(k2);
            res.setAverage((double) totalSum / v2.size());
            response.add(res);
        }));
        return  response;
    }
}
