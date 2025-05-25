
package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;


class UserMapper {

	static UserDto toDto(User user) {

		return new UserDto(user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getBirthdate(),
				user.getEmail());
	}
}
