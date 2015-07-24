package ba.pehli.cinema.service;

import java.util.List;

import ba.pehli.cinema.domain.User;

public interface UserDao {
	User findByUsername(String username);
	User getAuthenticatedUser();
	List<User> findAllNonVerifiedUsers();
	User findUserByVerificationCode(String verificationCode);
	User save(User user);
	void deleteNonVerifiedUsers();
	void delete(User user);
}
