package com.devops.moviecatalog.service;

import com.devops.moviecatalog.model.Rating;
import com.devops.moviecatalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://movie-rating-service/rating/user/" + userId, UserRating.class);
    }


    UserRating getFallBackUserRating(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(
                new Rating("", 0)
        ));
        return userRating;
    }
}
