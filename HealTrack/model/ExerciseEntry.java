package model;

import java.time.LocalDate;

public class ExerciseEntry extends HealthEntry {
    public ExerciseEntry(LocalDate date, double minutes, String notes) {
        super(date, "Exercise", minutes, notes);
    }
    
    @Override
    public String getSummary() {
        return String.format("Exercise: %.0f minutes - %s", getValue(), getNotes());
    }
}