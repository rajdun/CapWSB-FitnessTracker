/*
 * Created on 25-05-2025 11:35 by ajarzabe
 *
 * Copyright (c) 2001-2025 Univio S.A.
 * ul. Strzegomska 2-4, 53-611 Wrocław, Poland
 * Wszelkie prawa zastrzeżone
 *
 * Niniejsze oprogramowanie jest własnością Univio S.A.
 * Wykorzystanie niniejszego oprogramowania jest możliwe tylko na podstawie
 * i w zgodzie z warunkami umowy licencyjnej zawartej z Univio S.A.
 */

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
