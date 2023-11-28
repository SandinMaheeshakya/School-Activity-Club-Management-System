package com.main.EventCreation.services;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class EventServiceTest {

    @Test
    public void testValidateDateFormat_ValidFormat_NoErrors() {
        // Given
        String validDate = "2023-11-28";
        List<String> errors = new ArrayList<>();

        // When
        EventService eventService = new EventService();
        eventService.validateDateFormat(validDate, errors);

        // Then
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateDateFormat_InvalidFormat_ErrorAdded() {
        // Given
        String invalidDate = "28-11-2023";
        List<String> errors = new ArrayList<>();

        // When
        EventService eventService = new EventService();
        eventService.validateDateFormat(invalidDate, errors);

        // Then
        assertFalse(errors.isEmpty());
    }


    @Test
    public void testValidateTimeFormat_InvalidFormat_ErrorAdded() {
        // Given
        String invalidTime = "25:70";
        List<String> errors = new ArrayList<>();

        // When
        EventService eventService = new EventService();
        eventService.validateTimeFormat(invalidTime, "End Time", errors);

        // Then
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidateDuration_ValidDuration_NoErrors() {
        // Given
        String validDuration = "60";
        List<String> errors = new ArrayList<>();

        // When
        EventService eventService = new EventService();
        eventService.validateDuration(validDuration, errors);

        // Then
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateDuration_InvalidDuration_ErrorAdded() {
        // Given
        String invalidDuration = "zero";
        List<String> errors = new ArrayList<>();

        // When
        EventService validation = new EventService();
        validation.validateDuration(invalidDuration, errors);

        // Then
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidateTimeConsistency_ValidTimes_NoErrors() {
        // Given
        String startTime = "12:00";
        String endTime = "13:00";
        List<String> errors = new ArrayList<>();

        // When
        EventService validation = new EventService();
        validation.validateTimeConsistency(startTime, endTime, errors);

        // Then
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateTimeConsistency_EndTimeBeforeStartTime_ErrorAdded() {
        // Given
        String startTime = "14:00";
        String endTime = "12:00";
        List<String> errors = new ArrayList<>();

        // When
        EventService validation = new EventService();
        validation.validateTimeConsistency(startTime, endTime, errors);

        // Then
        assertFalse(errors.isEmpty());
    }


}
