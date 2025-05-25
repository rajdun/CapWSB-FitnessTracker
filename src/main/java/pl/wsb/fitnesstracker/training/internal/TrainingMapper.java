package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.training.api.Training;


@Component
public class TrainingMapper {

	static TrainingDto toDto(Training training) {

		return new TrainingDto(
			UserMapper.toDto(training.getUser()),
			training.getStartTime(),
			training.getEndTime(),
			training.getActivityType(),
			training.getDistance(),
			training.getAverageSpeed()
		);
	}
}
