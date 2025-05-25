package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingDto;


public interface TrainingService {
    TrainingDto putTraining(TrainingForm trainingForm);
    TrainingDto patchTraining(long id, TrainingForm trainingForm);
}
