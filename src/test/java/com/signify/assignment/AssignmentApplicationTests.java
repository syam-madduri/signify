package com.signify.assignment;


import com.signify.assignment.dto.AverageRatingDTO;
import com.signify.assignment.dto.ReviewDTO;
import com.signify.assignment.entity.Review;
import com.signify.assignment.repository.ReviewRepository;
import com.signify.assignment.service.ReviewService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class AssignmentApplicationTests {

	@InjectMocks
	private ReviewService service;

	@Mock
	private ReviewRepository repository;



	@Test
	@Order(1)
	void saveReviewTestCase(){
		Review review = new Review();
		review.setReviewed_date(new Date());
		review.setReview("Test Review");
		review.setAuthor("Signify");
		review.setProduct_name("iTunes");
		review.setRating(4);
		doReturn(review).when(repository).save(any());
		Review result = service.save(review);
		Assertions.assertEquals("iTunes", result.getProduct_name());
		Assertions.assertEquals("Signify", result.getAuthor());
		Assertions.assertEquals(4, result.getRating());
	}


	@Test
	@Order(2)
	void findResultsForResourceTestCase(){
		ReviewDTO review = new ReviewDTO();
		review.setReview_source("GooglePlayStore");
		doReturn(getReviewDTOList1()).when(repository).findReviews(review.getRating(), review.getReviewed_date(), review.getReview_source());
		List<ReviewDTO> result = service.find(review);
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals("GooglePlayStore", result.get(0).getReview_source());
	}


	@Test
	@Order(3)
	void findAllResultsTestCase(){
		ReviewDTO review = new ReviewDTO();
		doReturn(getReviewDTOList()).when(repository).findReviews(review.getRating(), review.getReviewed_date(), review.getReview_source());
		List<ReviewDTO> result = service.find(new ReviewDTO());
		Assertions.assertEquals(5, result.size());
	}

	@Test
	@Order(4)
	void groupByRatingsTestcase(){
		doReturn(groupByRatings()).when(repository).findAll();
		Map<Integer, Long> result = service.groupByRatings();
		Assertions.assertEquals(2, result.get(1).intValue());
		Assertions.assertEquals(5, result.size());
	}

	@Test
	@Order(5)
	void getAverageRatingsTestCase(){
		ReviewDTO review = new ReviewDTO();
		doReturn(getReviewDTOList()).when(repository).findReviews(review.getRating(), review.getReviewed_date(), review.getReview_source());
		List<AverageRatingDTO> result = service.getAverageRatings(new ReviewDTO());
		Assertions.assertEquals(result.get(0).getReviewed_date(), "2014-12-10T22:43:14.000Z");
		Assertions.assertEquals(result.get(0).getReview_source(), "GooglePlayStore");
		Assertions.assertEquals(result.get(0).getAverage(), 2.0);
	}


	private List<ReviewDTO> getReviewDTOList() {
		return List.of(
				new ReviewDTO("2017-12-10T22:43:14.000Z", "Test Review from Amazon alexa", "Signify", "GooglePlayStore", 5, "Super", "Amazon Alexa" ),
				new ReviewDTO("2017-12-10T22:40:14.000Z", "Test Review from iTunes", "Signify", "iTunes", 3, "Super", "iTunes" ),
				new ReviewDTO("2023-12-10T22:43:14.000Z", "This review used for test case", "Signify", "GooglePlayStore", 4, "Super", "Amazon Alexa" ),
				new ReviewDTO("2024-12-10T22:43:14.000Z", "This review used for test case", "Signify", "iTunes", 1, "Super", "iTunes" ),
				new ReviewDTO("2014-12-10T22:43:14.000Z", "This review used for test case", "Signify", "GooglePlayStore", 2, "Super", "Amazon Alexa" )
		);
	}

	private List<Review> groupByRatings() {
		return List.of(
				new Review(new Date(), "Test Review from Amazon alexa", "Signify", "GooglePlayStore", 2, "Super", "Amazon Alexa" ),
				new Review(new Date(), "Test Review from Amazon alexa", "Signify", "GooglePlayStore", 5, "Super", "Amazon Alexa" ),
				new Review(new Date(), "Test Review from iTunes", "Signify", "iTunes", 4, "Super", "iTunes" ),
				new Review(new Date(), "This review used for test case", "Signify", "GooglePlayStore", 3, "Super", "Amazon Alexa" ),
				new Review(new Date(), "This review used for test case", "Signify", "iTunes", 3, "Super", "iTunes" ),
				new Review(new Date(), "This review used for test case", "Signify", "GooglePlayStore", 1, "Super", "Amazon Alexa" ),
				new Review(new Date(), "This review used for test case", "Signify", "GooglePlayStore", 1, "Super", "Amazon Alexa" )
		);
	}


	private List<ReviewDTO> getReviewDTOList1() {
		return List.of(
				new ReviewDTO("2017-12-10T22:43:14.000Z", "Test Review from Amazon alexa", "Signify", "GooglePlayStore", 5, "Super", "Amazon Alexa" )
		);
	}
}
