package com.signify.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    Object reviewed_date;
    String review;
    String author;
    String review_source;
    int rating;
    String title;
    String product_name;
}
