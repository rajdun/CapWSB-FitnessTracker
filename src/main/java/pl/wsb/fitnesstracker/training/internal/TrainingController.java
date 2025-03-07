package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;


    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .toList();
    }

    @GetMapping("/{userId}")
    public List<Training> getTrainingsByUserId(@PathVariable long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .toList();
    }

    @GetMapping("/finished/{date}")
    public List<Training> getTrainingsAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return trainingService.getTrainingsAfter(date)
                .stream()
                .toList();
    }

    @GetMapping("/activityType")
    public List<Training> getByActivityType(@RequestParam("activityType") ActivityType activityType) {
        return trainingService.getByActivityType(activityType)
                .stream()
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody TrainingDto trainingDto) {
        var training = trainingMapper.toEntity(trainingDto);
        return trainingService.putTraining(training);
    }

    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable long trainingId, @RequestBody TrainingDto trainingDto) {
        var training = trainingMapper.toEntity(trainingDto);
        training.setId(trainingId);
        var updatedTraining = trainingService.patchTraining(training);
        return updatedTraining;
    }
}
