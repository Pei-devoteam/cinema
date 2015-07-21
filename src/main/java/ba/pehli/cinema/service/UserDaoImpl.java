package ba.pehli.cinema.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ba.pehli.cinema.domain.User;

@Service("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findUserByVerificationCode(String verificationCode) {
		return (User)sessionFactory.getCurrentSession().getNamedQuery("User.findByVerificationCode").setParameter("verificationCode", verificationCode).uniqueResult();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAllNonVerifiedUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("User.findAllNonVerified").list();
	}

	@Override
	public User save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	@Override
	public void deleteNonVerifiedUsers() {
		System.out.println(" *** BATCH");
		Calendar cal = Calendar.getInstance();
		Date now = new Date();
		for (User user : findAllNonVerifiedUsers()) {
			long difference = now.getTime() - user.getCreatedDate().getTime();
			long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			if (days > 5) {
				delete(user);
			}
		}
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
		
	}
	
	

}
