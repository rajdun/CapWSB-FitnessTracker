package pl.wsb.fitnesstracker.training.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;


public interface TrainingProvider {

	/**
	 * Retrieves a training based on their ID.
	 * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
	 *
	 * @param trainingId id of the training to be searched
	 * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
	 */
	Optional<User> getTraining(Long trainingId);

	/**
	 * Retrieves all trainings.
	 * @return A list of all trainings
	 */
	List<Training> getAllTrainings();

	/**
	 * Retrieves all trainings for a specific user.
	 * @param userId id of the user whose trainings are to be retrieved
	 * @return A list of trainings for the specified user
	 */
	List<Training> getTrainingsByUserId(long userId);

	/**
	 * Retrieves all trainings that ended after the specified date.
	 * @param date the date to compare with
	 * @return A list of trainings that ended after the specified date
	 */
	List<Training> getTrainingsAfter(Date date);

	/**
	 * Retrieves all trainings for a specific activity type.
	 * @param activityType the activity type to filter trainings by
	 * @return A list of trainings for the specified activity type
	 */
	List<Training> getByActivityType(ActivityType activityType);
}
