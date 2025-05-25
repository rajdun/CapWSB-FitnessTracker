package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingForm;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;


@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;
	private final TrainingProvider trainingProvider;

	@GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingProvider.getAllTrainings()
                .stream()
				.map(TrainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable long userId) {
        return trainingProvider.getTrainingsByUserId(userId)
                .stream()
			.map(TrainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{date}")
    public List<TrainingDto> getTrainingsAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return trainingProvider.getTrainingsAfter(date)
                .stream()
				.map(TrainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getByActivityType(@RequestParam("activityType") ActivityType activityType) {
        return trainingProvider.getByActivityType(activityType)
                .stream()
				.map(TrainingMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingForm trainingForm) {

		return trainingService.putTraining(trainingForm);
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable long trainingId, @RequestBody TrainingForm trainingForm) {

        return trainingService.patchTraining(trainingId, trainingForm);
    }
}
