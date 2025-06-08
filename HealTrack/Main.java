import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.*;

public class Main {

    public static void main(String[] args) {
        List<HealthEntry> entries = new ArrayList<>();
        User user = new User("saya", "password 123", "Saya saya");
        Tracker tracker = new Tracker();
        for (HealthEntry entry : entries) {
            tracker.addEntry(entry);
        }

        user.setHeight(160);
        user.setWeight(55);
        user.setTargetWeight(45);

        tracker.addEntry(new FoodEntry(LocalDate.of(2025, 6, 9), 550, "Sarapan"));
        tracker.addEntry(new ExerciseEntry(LocalDate.of(2025, 6, 9), 3.5, "Jogging"));
        tracker.addEntry(new SleepEntry(LocalDate.of(2025, 6, 8), 7.0, "Tidur cukup nyenyak"));
        tracker.addEntry(new WaterEntry(LocalDate.of(2025, 6, 8), 2.0, "Minum air putih"));
        tracker.addEntry(new FoodEntry(LocalDate.of(2025, 6, 10), 700, "Makan siang"));
        tracker.addEntry(new ExerciseEntry(LocalDate.of(2025, 6, 10), 2.0, "Senam sore"));
        tracker.addEntry(new SleepEntry(LocalDate.of(2025, 6, 10), 6.5, "Tidur malam"));
        tracker.addEntry(new WaterEntry(LocalDate.of(2025, 6, 10), 2.5, "Air mineral"));

        System.out.println("Username       : " + user.getUsername());
        System.out.println("Full Name      : " + user.getFullName());
        System.out.println("Height         : " + user.getHeight() + " cm");
        System.out.println("Weight         : " + user.getWeight() + " kg");
        System.out.println("Target Weight  : " + user.getTargetWeight() + " kg");
        System.out.printf("BMI            : %.2f\n", user.calculateBMI());
        System.out.println("BMI Category   : " + user.getBMICategory());

        for (HealthEntry entry : tracker.getAllEntries()) {
            System.out.println("Date  : " + entry.getDate());
            System.out.println("Entry : " + entry.getSummary());
        }

        LocalDate targetDate = LocalDate.of(2025, 6, 9);
        System.out.println("Ringkasan Harian: " + targetDate);
        Map<String, Double> dailySummary = tracker.getSummaryByDate(targetDate);
        for (String type : dailySummary.keySet()) {
            System.out.println(type + ": " + dailySummary.get(type));
        }

        LocalDate startDate = LocalDate.of(2025, 6, 8);
        System.out.println("Ringkasan Mingguan Mulai " + startDate);
        Map<LocalDate, Map<String, Double>> weeklySummary = tracker.getWeeklySummary(startDate);
        for (LocalDate date : weeklySummary.keySet()) {
            System.out.println("Tanggal: " + date);
            Map<String, Double> summary = weeklySummary.get(date);
            for (String type : summary.keySet()) {
                System.out.println("  " + type + ": " + summary.get(type));
            }
        }
    }
}
