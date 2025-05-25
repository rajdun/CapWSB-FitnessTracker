package pl.wsb.fitnesstracker.training.api;

import java.util.Date;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;


@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_training_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL"))
	private User user;

	@Column(name = "start_time", nullable = false)
	private Date startTime;

	@Column(name = "end_time", nullable = false)
	private Date endTime;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "activity_type", nullable = false)
	private ActivityType activityType;

	@Column(name = "distance")
	private double distance;

	@Column(name = "average_speed")
	private double averageSpeed;

	public Training(
		final User user,
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

	public void update(
		final User user,
		final Date startTime,
		final Date endTime,
		final ActivityType activityType,
		final double distance,
		final double averageSpeed) {

		Assert.notNull(user, "user must not be null");
		Assert.notNull(startTime, "startTime must not be null");
		Assert.notNull(endTime, "endTime must not be null");
		Assert.notNull(activityType, "activityType must not be null");

		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityType = activityType;
		this.distance = distance;
		this.averageSpeed = averageSpeed;
	}
}