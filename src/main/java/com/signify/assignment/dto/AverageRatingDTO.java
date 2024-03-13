package com.signify.assignment.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AverageRatingDTO {

    String reviewed_date;
    String review_source;
    double average;
}
