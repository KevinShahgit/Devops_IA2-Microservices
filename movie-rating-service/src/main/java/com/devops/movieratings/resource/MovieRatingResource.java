package com.devops.movieratings.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.movieratings.model.Rating;
import com.devops.movieratings.model.UserRating;

@RestController

public class MovieRatingResource {
    @RequestMapping("/rating/{movieId}/{rate}")
    Rating getMovieRating(@PathVariable("movieId") String movieId, @PathVariable("rate") int rate) {
        return new Rating(movieId, rate);
    }
    
    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }

    
}


