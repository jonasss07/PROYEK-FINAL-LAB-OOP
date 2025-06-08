package model;

import java.time.LocalDate;

public class FoodEntry extends HealthEntry {

    public FoodEntry(LocalDate date, double calories, String notes) {
        super(date, "Food", calories, notes);
    }

    @Override
    public String getSummary() {
        return String.format("Food: %.0f calories - %s", getValue(), getNotes());
    }
}
