package ba.pehli.cinema.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ba.pehli.cinema.domain.Movie;
import ba.pehli.cinema.domain.Rating;
import ba.pehli.cinema.domain.User;


@Service("movieDao")
@Transactional
public class MovieDaoImpl implements MovieDao{
	
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public MovieDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movie> findAll() {
		return sessionFactory.getCurrentSession().createQuery("select m from Movie m").list();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Movie> findAllWithCast() {
		return sessionFactory.getCurrentSession().getNamedQuery("Movie.findAllWithCast").list();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Movie> findAllWithCastAndRating() {
		return sessionFactory.getCurrentSession().getNamedQuery("Movie.findAllWithCastAndRatingForUser").list();
	}

	@Override
	@Transactional(readOnly=true)
	public Movie findById(int id) {
		return (Movie)sessionFactory.getCurrentSession().getNamedQuery("Movie.findById").setParameter("id", id).uniqueResult();
	}

	@Override
	public Movie save(Movie movie) {
		sessionFactory.getCurrentSession().saveOrUpdate(movie);
		return movie;
	}

	@Override
	public void delete(Movie movie) {
		sessionFactory.getCurrentSession().delete(movie);
	}

	@Override
	public Rating findRating(Movie movie, User user) {
		return (Rating) sessionFactory.getCurrentSession().getNamedQuery("Rating.findForUserAndMovie").setParameter("userId", user.getId()).setParameter("movieId", movie.getId()).uniqueResult();
	}
	
}
