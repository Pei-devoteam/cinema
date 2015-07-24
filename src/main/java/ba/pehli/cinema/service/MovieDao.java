package ba.pehli.cinema.service;

import java.util.List;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.domain.Rating;
import ba.pehli.cinema.domain.User;

public interface MovieDao {
	List<Movie> findAll();
	List<Movie> findAllWithCast();
	List<Movie> findAllWithCastAndRating();
	Movie findById(int id);
	Movie save(Movie movie);
	void delete(Movie movie);
}
