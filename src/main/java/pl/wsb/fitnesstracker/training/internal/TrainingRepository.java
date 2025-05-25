package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.wsb.fitnesstracker.training.api.Training;


interface TrainingRepository extends JpaRepository<Training, Long> {

	List<Training> findByUserId(long userId);

	List<Training> findByStartTimeAfter(Date date);

	List<Training> findByActivityType(ActivityType activityType);

	List<Training> findByEndTimeAfter(Date date);
}
