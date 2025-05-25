package pl.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

	private final UserServiceImpl userService;

	private final UserMapper userMapper;

	/**
	 * Endpoint to get all users.
	 *
	 * @return list of all users
	 */
	@GetMapping
	public List<UserDto> getAllUsers() {

		return userService.findAllUsers()
			.stream()
			.map(userMapper::toDto)
			.toList();
	}

	/**
	 * Endpoint to add a new user.
	 * @param userDto user data transfer object
	 * @return created user data transfer object
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto addUser(@RequestBody UserDto userDto) {

		var user = userMapper.toEntity(userDto);
		var savedUser = userService.createUser(user);
		return userMapper.toDto(savedUser);
	}

	/**
	 * Endpoint to get all users with only id, first name and last name.
	 * @return list of all users with only id, first name and last name
	 */
	@GetMapping("/simple")
	public List<UserSimpleDto> getAllUsersSimple() {

		return userService.findAllUsers()
			.stream()
			.map(userMapper::toSimpleDto)
			.toList();
	}

	/**
	 * Endpoint to get user by id.
	 * @param id user id
	 * @return user data transfer object
	 */
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable Long id) {

		return userMapper.toDto(userService.findById(id));
	}

	/**
	 * Endpoint to get user by email.
	 * @param email user email
	 * @return list of users with the same email
	 */
	@GetMapping("/email")
	public List<UserDto> getUserByEmail(@RequestParam("email") String email) {

		return userService.getUserByEmail(email)
			.stream()
			.map(userMapper::toDto)
			.toList();
	}

	/**
	 * Endpoint to get users older than a given date.
	 * @param time date to compare with
	 * @return list of users older than the given date
	 */
	@GetMapping("/older/{time}")
	public List<UserDto> getUsersOlderThan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {

		return userService.getUsersOlderThan(time).stream().map(userMapper::toDto).toList();
	}

	/**
	 * Endpoint to delete user by id.
	 * @param id user id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {

		userService.deleteUser(id);
	}

	/**
	 * Endpoint to update user by id.
	 * @param id user id
	 * @param userDto user data transfer object
	 * @return updated user data transfer object
	 */
	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

		var user = userMapper.toEntity(userDto);
		user.setId(id);

		return userMapper.toDto(userService.updateUser(user));
	}
}