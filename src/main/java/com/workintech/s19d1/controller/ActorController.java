package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;

    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    @PostMapping
    public Actor save(@Validated @RequestBody ActorRequest actorRequest) {
        List<Movie> movies = actorRequest.getMovies();
        for(Movie movie : movies) {
            actorRequest.getActor().addMovie(movie);
        }
        actorService.save(actorRequest.getActor());

        return actorRequest.getActor();
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor newActor) {
        Actor actor = actorService.findById(id);
        actor.setFirstName(newActor.getFirstName());
        actor.setLastName(newActor.getLastName());
        actor.setBirthDate(newActor.getBirthDate());
        actor.setMovies(newActor.getMovies());
        actor.setGender(newActor.getGender());
        return actor;
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable Long id) {
        //List<Movie> movies = movieService.findAll();
        Actor actor = actorService.findById(id);

        /*for(Movie movie : movies) {
            movie.getActors().remove(actor);
        }*/

        actorService.delete(actor);
        return actor;
    }

}
