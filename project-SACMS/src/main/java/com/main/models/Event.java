package com.main.models;

public class Event {
    private String eventID;
    private String eventName;
    private String description;
    private String eventType;
    private String eventDate;
    private String startTime;
    private String duration; // duration in minutes
    private String endTime;
    private String mode;
    private String physicalLocation;
    private String onlineMeetingLink;
    private String onlineMeetingID;
    private String onlineMeetingPassword;

    public Event() {
    }

    public Event(String eventID, String eventName, String description, String eventType, String eventDate, String startTime, String duration, String endTime, String mode, String physicalLocation, String onlineMeetingLink, String onlineMeetingID, String onlineMeetingPassword) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.description = description;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.mode = mode;
        this.physicalLocation = physicalLocation;
        this.onlineMeetingLink = onlineMeetingLink;
        this.onlineMeetingID = onlineMeetingID;
        this.onlineMeetingPassword = onlineMeetingPassword;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getOnlineMeetingLink() {
        return onlineMeetingLink;
    }

    public void setOnlineMeetingLink(String onlineMeetingLink) {
        this.onlineMeetingLink = onlineMeetingLink;
    }

    public String getOnlineMeetingID() {
        return onlineMeetingID;
    }

    public void setOnlineMeetingID(String onlineMeetingID) {
        this.onlineMeetingID = onlineMeetingID;
    }

    public String getOnlineMeetingPassword() {
        return onlineMeetingPassword;
    }

    public void setOnlineMeetingPassword(String onlineMeetingPassword) {
        this.onlineMeetingPassword = onlineMeetingPassword;
    }
}