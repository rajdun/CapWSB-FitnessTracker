package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;


/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

	/**
	 * Creates a new user.
	 *
	 * @param user the user to create
	 * @return the created user
	 */
	User createUser(User user);

	/**
	 * Updates an existing user.
	 *
	 * @param id the user to update
	 * @return the updated user
	 */
	User findById(Long id);

	/**
	 * Gets all users with birthdate before the specified date.
	 * @param time - date to compare with
	 * @return list of users with birthdate before the specified date
	 * */
	List<User> getUsersOlderThan(LocalDate time);
}
