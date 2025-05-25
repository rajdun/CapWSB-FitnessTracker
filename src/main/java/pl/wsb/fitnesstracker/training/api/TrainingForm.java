package pl.wsb.fitnesstracker.training.api;

import java.util.Date;

import org.springframework.util.Assert;

import pl.wsb.fitnesstracker.training.internal.ActivityType;


public class TrainingForm {

	private final long userId;

	private final Date startTime;

	private final Date endTime;

	private final ActivityType activityType;

	private final double distance;

	private final double averageSpeed;

	TrainingForm(long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {

		Assert.notNull(startTime, "startTime must not be null");
		Assert.notNull(endTime, "endTime must not be null");
		Assert.notNull(activityType, "activityType must not be null");

		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityType = activityType;
		this.distance = distance;
		this.averageSpeed = averageSpeed;
	}

	public long getUserId() {

		return userId;
	}

	public Date getStartTime() {

		return startTime;
	}

	public Date getEndTime() {

		return endTime;
	}

	public ActivityType getActivityType() {

		return activityType;
	}

	public double getDistance() {

		return distance;
	}

	public double getAverageSpeed() {

		return averageSpeed;
	}
}
