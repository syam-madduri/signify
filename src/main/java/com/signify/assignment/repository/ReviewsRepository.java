package com.signify.assignment.repository;

import com.signify.assignment.dto.ReviewDTO;
import com.signify.assignment.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ReviewsRepository extends JpaRepository<Reviews, Date> {

    @Query("select new com.signify.assignment.dto.ReviewDTO (to_char(reviewed_date, 'dd-mm-yyyy hh:mm:ss') , review, author, review_source, rating, title, product_name) " +
            "from Reviews where (rating = :rating or 0 = :rating) and (to_char(reviewed_date , 'dd-mm-yyyy hh:mm:ss') = :reviewed_date or :reviewed_date is null) " +
            "and (review_source = :review_source or :review_source is null)")
    List<ReviewDTO> findReviews(int rating, Object reviewed_date, String review_source);
}