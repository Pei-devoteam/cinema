package ba.pehli.cinema.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.domain.Rating;
import ba.pehli.cinema.domain.User;
import ba.pehli.cinema.service.MovieDao;
import ba.pehli.cinema.service.RatingDao;
import ba.pehli.cinema.service.UserDao;

@Controller
@RequestMapping(value="/movies")
public class MovieController {
	
	private MovieDao movieDao;
	private UserDao userDao;
	private RatingDao ratingDao;
	
	@Autowired
	public MovieController(MovieDao movieDao,UserDao userDao,RatingDao ratingDao) {
		this.movieDao = movieDao;
		this.userDao = userDao;
		this.ratingDao = ratingDao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {
		User user = userDao.getAuthenticatedUser();
		List<Movie> movies = movieDao.findAllWithCast();
		Map<Integer, Integer> ratings = new HashMap<Integer, Integer>();
		
		if (user != null) {
			for (Movie movie : movies) {
				Rating rating = ratingDao.findByMovieAndUser(movie, user); 
				ratings.put(movie.getId(), rating != null ? rating.getRating() : 0);
			}
		}
		model.addAttribute("movies", movies);
		model.addAttribute("ratings", ratings);
		System.out.println(ratings);
		return "movies/list";
	}
	
	@RequestMapping(value="/photo/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] downloadImage(@PathVariable("id") int id) {
		Movie movie = movieDao.findById(id);
		return movie.getImage();
	}
	
	@RequestMapping(value="/rating", method=RequestMethod.GET)
	@ResponseBody 
	public int rateMovie(int movieId, int rating) {
		User user = userDao.getAuthenticatedUser();
		Movie movie = movieDao.findById(movieId);
		Rating rt = ratingDao.findByMovieAndUser(movie, user);
		if (rt != null) {
			rt.setRating(rating);
		} else {
			rt = new Rating();
			rt.setMovie(movie);
			rt.setUser(user);
			rt.setRating(rating);
		}
		ratingDao.save(rt);
		return rt.getId(); 
	}
}
