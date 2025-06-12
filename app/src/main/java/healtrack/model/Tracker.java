package healtrack.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tracker {
    private final List<HealthEntry> entries = new ArrayList<>();

    public void addEntry(HealthEntry entry) {
        entries.add(entry);
    }

    public List<HealthEntry> getEntriesByDate(LocalDate date) {
        return entries.stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<HealthEntry> getWeeklySummary(LocalDate fromDate) {
        return entries.stream()
                .filter(e -> !e.getDate().isBefore(fromDate.minusDays(6)))
                .collect(Collectors.toList());
    }

    public List<HealthEntry> getAllEntries() {
        return entries;
    }
}
