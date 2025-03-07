package pl.wsb.fitnesstracker.training.api;

public interface TrainingService {
    Training putTraining(Training training);
    Training patchTraining(Training training);
}
