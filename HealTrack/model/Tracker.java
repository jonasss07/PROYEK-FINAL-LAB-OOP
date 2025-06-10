package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {
    private List<HealthEntry> entries;

    public Tracker() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(HealthEntry entry) {
        entries.add(entry);
    }

    public List<HealthEntry> getEntriesByDate(LocalDate date) {
        List<HealthEntry> dayEntries = new ArrayList<>();
        for (HealthEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                dayEntries.add(entry);
            }
        }
        return dayEntries;
    }

    public Map<String, Double> getSummaryByDate(LocalDate date) {
        Map<String, Double> summary = new HashMap<>();
        summary.put("Food", 0.0);
        summary.put("Exercise", 0.0);
        summary.put("Sleep", 0.0);
        summary.put("Water", 0.0);
        
        for (HealthEntry entry : getEntriesByDate(date)) {
            summary.put(entry.getType(), summary.get(entry.getType()) + entry.getValue());
        }
        
        return summary;
    }

    public Map<LocalDate, Map<String, Double>> getWeeklySummary(LocalDate startDate) {
        Map<LocalDate, Map<String, Double>> weeklySummary = new HashMap<>();
        
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            weeklySummary.put(date, getSummaryByDate(date));
        }
        
        return weeklySummary;
    }

    public List<HealthEntry> getAllEntries() {
        return new ArrayList<>(entries);
    }
}
