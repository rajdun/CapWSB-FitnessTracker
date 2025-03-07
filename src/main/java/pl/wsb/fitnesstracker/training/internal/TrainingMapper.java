package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

@Component
public class TrainingMapper {

    private final UserRepository userRepository;

    public TrainingMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    Training toEntity(TrainingDto trainingDto) {

        var user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + trainingDto.getUserId() + " not found!"));

        return new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }

}
