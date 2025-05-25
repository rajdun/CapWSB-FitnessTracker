package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.UserDto;


@Getter
@ToString
public class TrainingDto {
    private final UserDto user;

    private final Date startTime;

    private final Date endTime;

    private final ActivityType activityType;

    private final double distance;

    private final double averageSpeed;

    public TrainingDto(
            final UserDto user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
