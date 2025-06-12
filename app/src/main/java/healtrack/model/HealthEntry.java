package healtrack.model;

import java.time.LocalDate;

public abstract class HealthEntry {
    protected LocalDate date;
    protected String type;
    protected double value;
    protected String notes;

    public HealthEntry(LocalDate date, String type, double value, String notes) {
        this.date = date;
        this.type = type;
        this.value = value;
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public String getNotes() {
        return notes;
    }

    public abstract String getSummary();
}