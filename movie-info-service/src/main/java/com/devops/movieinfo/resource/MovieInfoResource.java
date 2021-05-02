package com.devops.movieinfo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devops.movieinfo.model.Movie;
import com.devops.movieinfo.model.MovieSummary;

@RestController
@RequestMapping("/movie")
public class MovieInfoResource {

    @Autowired
    private RestTemplate restTemplate;

    private String apiKey = "34475b6af16f8c70013bff39fa305b49";

    @RequestMapping("{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
