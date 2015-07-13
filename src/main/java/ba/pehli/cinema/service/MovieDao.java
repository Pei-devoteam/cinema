package ba.pehli.cinema.service;

import java.util.List;

import ba.pehli.cinema.domain.Movie;

public interface MovieDao {
	List<Movie> findAll();
	List<Movie> findAllWithCast();
	Movie findById(int id);
	Movie save(Movie movie);
	void delete(Movie movie);
}
