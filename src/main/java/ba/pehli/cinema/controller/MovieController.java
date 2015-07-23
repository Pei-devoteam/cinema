package ba.pehli.cinema.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import ba.pehli.cinema.service.UserDao;

@Controller
@RequestMapping(value="/movies")
public class MovieController {
	
	private MovieDao movieDao;
	private UserDao userDao;
	
	@Autowired
	public MovieController(MovieDao movieDao,UserDao userDao) {
		this.movieDao = movieDao;
		this.userDao = userDao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {
		User user = userDao.findByUsername("user");
		List<Movie> movies = movieDao.findAllWithCast();
		Map<Integer, Integer> ratings = new HashMap<Integer, Integer>();
		for (Movie m : movies) {
			Rating rating = movieDao.findRating(m, user); 
			ratings.put(m.getId(), rating != null ? rating.getRating() : 0);
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
}
