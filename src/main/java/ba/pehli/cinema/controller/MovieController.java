package ba.pehli.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.service.MovieDao;

@Controller
@RequestMapping(value="/movies")
public class MovieController {
	
	private MovieDao movieDao;
	
	@Autowired
	public MovieController(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("message","Lista filmova (" + movieDao.findAll().size() + ")");
		model.addAttribute("movies", movieDao.findAllWithCast());
		return "movies/list";
	}
	
	@RequestMapping(value="/photo/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] downloadImage(@PathVariable("id") int id) {
		Movie movie = movieDao.findById(id);
		return movie.getImage();
	}
}
