package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingDto;


public interface TrainingService {

	/**
	 * Retrieves a training by its ID.
	 *
	 * @param id the ID of the training
	 * @return the training details
	 */
	TrainingDto putTraining(TrainingForm trainingForm);

	/**
	 * Updates an existing training with the given ID.
	 *
	 * @param id the ID of the training to update
	 * @param trainingForm the form containing updated training details
	 * @return the updated training details
	 */
	TrainingDto patchTraining(long id, TrainingForm trainingForm);
}
