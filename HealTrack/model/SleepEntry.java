package model;

import java.time.LocalDate;

public class SleepEntry extends HealthEntry {
    public SleepEntry(LocalDate date, double hours, String notes) {
        super(date, "Sleep", hours, notes);
    }
    
    @Override
    public String getSummary() {
        return String.format("Sleep: %.1f hours - %s", getValue(), getNotes());
    }
}
