package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingForm;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;


@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

	private final TrainingRepository trainingRepository;
	private final UserProvider userProvider;


	@Override
	public Optional<User> getTraining(final Long trainingId) {

		log.info("Get training with id {}", trainingId);
		var training = trainingRepository.findById(trainingId);
		return training.map(Training::getUser);
	}

	@Override
	public List<Training> getAllTrainings() {

		return trainingRepository.findAll();
	}

	@Override
	public List<Training> getTrainingsByUserId(long userId) {

		return trainingRepository.findByUserId(userId);
	}

	@Override
	public List<Training> getTrainingsAfter(Date date) {

		return trainingRepository.findByEndTimeAfter(date);
	}

	@Override
	public List<Training> getByActivityType(ActivityType activityType) {

		return trainingRepository.findByActivityType(activityType);
	}

	@Override
	public TrainingDto putTraining(TrainingForm trainingForm) {

		Assert.notNull(trainingForm, "trainingForm must not be null");

		User user = userProvider.getUser(trainingForm.getUserId())
			.orElseThrow(() -> new IllegalArgumentException("User with ID " + trainingForm.getUserId() + " not found!"));

		Training training = new Training(user,
			trainingForm.getStartTime(),
			trainingForm.getEndTime(),
			trainingForm.getActivityType(),
			trainingForm.getDistance(),
			trainingForm.getAverageSpeed());

		return TrainingMapper.toDto(trainingRepository.save(training));
	}

	@Override
	public TrainingDto patchTraining(long id, TrainingForm trainingForm) {

		Assert.notNull(trainingForm, "trainingForm must not be null");

		Training training = trainingRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Training with id " + id + " not found!"));

		User user = userProvider.getUser(trainingForm.getUserId())
			.orElseThrow(() -> new IllegalArgumentException("User with ID " + trainingForm.getUserId() + " not found!"));

		training.update(user,
			trainingForm.getStartTime(),
			trainingForm.getEndTime(),
			trainingForm.getActivityType(),
			trainingForm.getDistance(),
			trainingForm.getAverageSpeed());

		return TrainingMapper.toDto(trainingRepository.save(training));
	}
}
