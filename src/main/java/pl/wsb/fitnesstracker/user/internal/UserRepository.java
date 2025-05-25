package pl.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.wsb.fitnesstracker.user.api.User;


public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Query searching users by email address. It matches by exact match.
	 *
	 * @param email email of the user to search
	 * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
	 */
	default List<User> findByEmail(String email) {

		return findAll().stream()
			.filter(user -> Objects.equals(user.getEmail(), email))
			.toList();
	}

	List<User> findByBirthdateBefore(LocalDate birthdate);
}
