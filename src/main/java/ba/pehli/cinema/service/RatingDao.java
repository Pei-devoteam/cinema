package ba.pehli.cinema.service;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.domain.Rating;
import ba.pehli.cinema.domain.User;

public interface RatingDao {
	Rating findById(int id);
	Rating findByMovieAndUser(Movie movie,User user);
	Rating save(Rating rating);
}
