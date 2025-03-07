package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

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
    public Training putTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training patchTraining(Training training) {
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training ID is required for update!");
        }
        return trainingRepository.save(training);
    }
}
