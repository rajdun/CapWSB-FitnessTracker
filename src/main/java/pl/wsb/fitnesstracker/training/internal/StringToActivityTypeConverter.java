package pl.wsb.fitnesstracker.training.internal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToActivityTypeConverter implements Converter<String, ActivityType> {

	@Override
	public ActivityType convert(String source) {
		// Trim whitespace and handle null/empty cases
		String normalizedInput = source.trim().toUpperCase();

		for (ActivityType type : ActivityType.values()) {
			// Match enum name (e.g., "WALKING") or display name (e.g., "Walking")
			boolean isEnumNameMatch = type.name().equalsIgnoreCase(normalizedInput);
			boolean isDisplayNameMatch = type.getDisplayName().equalsIgnoreCase(source);

			if (isEnumNameMatch || isDisplayNameMatch) {
				return type;
			}
		}

		throw new IllegalArgumentException("Invalid activity type: " + source);
	}
}