# Signify assignment API's

1. Runs on default port : 8080
2. Using java 17, spring boot 3.2.3 versions.
3. To validate APIs on 3rd party system browsers please uncomment server.address and place your system IP.
4. Once after placing ip, in url instead of localhost enter IP address.


## API endpoints as below

### 1. Save Review
    HttpMethod : POST
    url : http://localhost:8080/review
    body:   {
                  "review": "Test review",
                  "author": "Signify",
                  "review_source": "iTunes",
                  "rating": 4,
                  "title": "Signify Review",
                  "product_name": "Apple Phone"
            }
### 2. Fetch Reviews ( With filters)
    HttpMethod : GET
    url : http://localhost:8080/reviews?reviewed_date=15-02-2018 07:02:03&rating=4&review_source=iTunes

### 3. Fetch Reviews ( Without filters)
       HttpMethod : GET
       url : http://localhost:8080/reviews
### 4. Fetch monthly average ratings
       HttpMethod : GET
       url : http://localhost:8080/getAverageRatings
### 5. Fetch Grouping by total ratings
       HttpMethod : GET
       url : http://localhost:8080/groupByRatings