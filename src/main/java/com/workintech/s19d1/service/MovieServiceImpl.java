package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.orElseThrow(() ->
                new ApiException("Movie is not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(Movie movie) {
        movieRepository.delete(movie);
        return movie;
    }
}
