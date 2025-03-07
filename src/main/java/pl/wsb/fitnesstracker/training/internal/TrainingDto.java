package pl.wsb.fitnesstracker.training.internal;

import lombok.Getter;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Getter
@ToString
public class TrainingDto {
    private long userId;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;

    public TrainingDto(
            final long user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.userId = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
