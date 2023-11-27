package com.main.projectsacms.EventCreation.services;

import com.main.projectsacms.EventCreation.models.Event;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for event-related business logic and validations.
 */
public class EventService {

    /**
     * Validates an event object's properties.
     *
     * @param event The event object to validate.
     * @throws IllegalArgumentException if any validation fails.
     */
    public void validateEvent(Event event) throws IllegalArgumentException {
        List<String> errors = new ArrayList<>();

        // Non-null validation for event name
        if (event.getEventName() == null || event.getEventName().trim().isEmpty()) {
            errors.add("Event name cannot be empty.");
        }

        // Length validation for event name
        if (event.getEventName().length() > 50) {
            errors.add("Event name cannot exceed 50 characters.");
        }

        // Date format validation
        validateDateFormat(event.getEventDate(), errors);

        // Time format validation for start and end times
        validateTimeFormat(event.getStartTime(), "Start time", errors);
        validateTimeFormat(event.getEndTime(), "End time", errors);

        // Numeric validation for duration
        validateDuration(event.getDuration(), errors);

        // Consistency validation for start and end times
        validateTimeConsistency(event.getStartTime(), event.getEndTime(), errors);

        // Business logic validation for 'online' mode
        if ("online".equalsIgnoreCase(event.getMode())) {
            if (event.getOnlineMeetingLink() == null || event.getOnlineMeetingLink().trim().isEmpty()) {
                errors.add("An online event must have a meeting link.");
            }
        }

        // Business logic validation for 'physical' mode
        if ("physical".equalsIgnoreCase(event.getMode())) {
            if (event.getPhysicalLocation() == null || event.getPhysicalLocation().trim().isEmpty()) {
                errors.add("A physical event must have a location.");
            }
        }
        }

    private void validateDateFormat(String date, List<String> errors) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (Exception e) {
            errors.add("Date must be in the format YYYY-MM-DD.");
        }
    }

    private void validateTimeFormat(String time, String fieldName, List<String> errors) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);
        try {
            timeFormat.parse(time);
        } catch (Exception e) {
            errors.add(fieldName + " must be in the format HH:mm.");
        }
    }

    private void validateDuration(String duration, List<String> errors) {
        try {
            int durationMinutes = Integer.parseInt(duration);
            if (durationMinutes <= 0) {
                errors.add("Duration must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            errors.add("Duration must be a valid number.");
        }
    }

    private void validateTimeConsistency(String startTime, String endTime, List<String> errors) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        if (end.isBefore(start)) {
            errors.add("End time cannot be before start time.");
        }

    }

}

