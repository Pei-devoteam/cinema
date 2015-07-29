package ba.pehli.cinema.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.domain.Rating;
import ba.pehli.cinema.domain.User;
import ba.pehli.cinema.service.MovieDao;
import ba.pehli.cinema.service.RatingDao;
import ba.pehli.cinema.service.UserDao;

@Controller
@RequestMapping(value="/movies")
public class MovieController{
	
	private MovieDao movieDao;
	private UserDao userDao;
	private RatingDao ratingDao;
	private MessageSource messageSource;
	
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
		return "movies/list";
	}
	
	@RequestMapping(value="/photo/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] downloadImage(@PathVariable("id") int id) {
		Movie movie = movieDao.findById(id);
		if (movie != null)
			return movie.getImage();
		return null;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String showEdit(@PathVariable int id,Model model) {
		Movie movie = movieDao.findById(id);
		model.addAttribute("movie", movie);
		return "movies/edit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String showNew(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		return "movies/edit";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String edit(@Valid Movie movie,BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,
				Locale locale,@RequestParam(value="image",required=false) Part image) {
		if (bindingResult.hasErrors()) {
			String message = messageSource.getMessage("movies.edit.error", null, locale);
			model.addAttribute("message", message);
			return "movies/edit"; 
		}
		
		if (image != null) {
			byte[] imageContent = null;
			try {
				InputStream is = image.getInputStream();
				if (is != null) {
					imageContent = IOUtils.toByteArray(is);
					movie.setImage(imageContent);
				}
			} catch (IOException e) {
				
			}
		}
		
		movieDao.save(movie);
		
		String message = messageSource.getMessage("form.success", null, locale);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/movies/edit/"+movie.getId();
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newMovie(@Valid Movie movie,BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,
				Locale locale,@RequestParam(value="image",required=false) Part image) {
		if (bindingResult.hasErrors()) {
			String message = messageSource.getMessage("movies.edit.error", null, locale);
			model.addAttribute("message", message);
			return "movies/edit"; 
		}
		
		if (image != null) {
			byte[] imageContent = null;
			try {
				InputStream is = image.getInputStream();
				if (is != null) {
					imageContent = IOUtils.toByteArray(is);
					movie.setImage(imageContent);
				}
			} catch (IOException e) {
				
			}
		}
		
		movieDao.save(movie);
		
		String message = messageSource.getMessage("form.success", null, locale);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/movies/edit/"+movie.getId();
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
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
}
