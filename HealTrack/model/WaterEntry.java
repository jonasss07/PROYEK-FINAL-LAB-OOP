package model;

import java.time.LocalDate;

public class WaterEntry extends HealthEntry {
    public WaterEntry(LocalDate date, double glasses, String notes) {
        super(date, "Water", glasses, notes);
    }
    
    @Override
    public String getSummary() {
        return String.format("Water: %.0f glasses - %s", getValue(), getNotes());
    }
}