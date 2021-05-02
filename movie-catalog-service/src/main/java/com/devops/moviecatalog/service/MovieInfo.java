package com.devops.moviecatalog.service;

import com.devops.moviecatalog.model.CatalogItem;
import com.devops.moviecatalog.model.Movie;
import com.devops.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
        System.out.println(rating.getMovieId());
        System.out.println(movie.getDescription() + " " + movie.getMovieId() + " " + movie.getName());
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    CatalogItem getFallBackCatalogItem(Rating rating) {
        return new CatalogItem("", "", 0);
    }
}
