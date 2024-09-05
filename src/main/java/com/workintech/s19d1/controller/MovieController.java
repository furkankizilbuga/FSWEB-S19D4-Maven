package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;
    private ActorService actorService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody Movie movie) {
        List<Actor> actors = movie.getActors();
        for(Actor actor : actors) {
            actor.getMovies().add(movie);
        }
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie newMovie) {
        Movie movie = movieService.findById(id);
        movie.setName(newMovie.getName());
        movie.setRating(newMovie.getRating());
        movie.setDirectorName(newMovie.getDirectorName());
        movie.setReleaseDate(newMovie.getReleaseDate());
        movie.setActors(newMovie.getActors());
        return movie;
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        List<Actor> actors = actorService.findAll();
        Movie movie = movieService.findById(id);

        for(Actor actor : actors) {
            actor.getMovies().remove(movie);
        }

        movieService.delete(movie);
        return movie;
    }

}
