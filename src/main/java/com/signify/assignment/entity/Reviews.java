package com.signify.assignment.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviews {
    @Id
    Date reviewed_date;
    @Lob
    String review;
    String author;
    String review_source;
    int rating;
    String title;
    String product_name;
}