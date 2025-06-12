package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Tambahan untuk pengingat!
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.scene.control.Alert;

public class DashboardView {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label kaloriLabel;
    @FXML
    private Label olahragaLabel;
    @FXML
    private Label tidurLabel;
    @FXML
    private Label airLabel;
    @FXML
    private PieChart activityPieChart;
    @FXML
    private Label motivasiLabel; // Tambahan: Label untuk motivasi

    private User currentUser;

    // Variabel untuk pengingat pop up
    private Timeline reminderTimeline;

    public void setUser(User user) {
        this.currentUser = user;
        updateDashboard();
        startReminder(); // Panggil pengingat setiap kali dashboard dibuka!
    }

    private void updateDashboard() {
        if (currentUser == null) return;

        // Set welcome
        welcomeLabel.setText("Selamat datang, " + currentUser.getUsername() + "!");

        // Siapkan summary
        Map<String, Double> summary = new HashMap<>();
        summary.put("Makan", 0.0);
        summary.put("Olahraga", 0.0);
        summary.put("Tidur", 0.0);
        summary.put("Air", 0.0);

        LocalDate today = LocalDate.now();

        // Baca data dari file, filter user & tanggal hari ini
        try (BufferedReader reader = new BufferedReader(new FileReader("health_entries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5 && parts[4].equals(currentUser.getUsername())) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String tipe = parts[1];
                    double nilai = Double.parseDouble(parts[2]);
                    if (date.equals(today) && summary.containsKey(tipe)) {
                        summary.put(tipe, summary.get(tipe) + nilai);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update label
        kaloriLabel.setText(String.valueOf(summary.get("Makan").intValue()));
        olahragaLabel.setText(String.valueOf(summary.get("Olahraga").intValue()));
        tidurLabel.setText(String.valueOf(summary.get("Tidur")));
        airLabel.setText(String.valueOf(summary.get("Air").intValue()));

        // Update PieChart
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Makan", summary.get("Makan")),
                new PieChart.Data("Olahraga", summary.get("Olahraga")),
                new PieChart.Data("Tidur", summary.get("Tidur")),
                new PieChart.Data("Air", summary.get("Air"))
        );

        // PieChart tetap muncul meski semua 0
        boolean allZero = pieData.stream().allMatch(d -> d.getPieValue() == 0);
        if (allZero) {
            pieData.clear();
            pieData.add(new PieChart.Data("Belum ada data", 1));
            activityPieChart.setLabelsVisible(false);
            activityPieChart.setLegendVisible(false);
        } else {
            activityPieChart.setLabelsVisible(true);
            activityPieChart.setLegendVisible(true);
        }
        activityPieChart.setData(pieData);

        // ==== FITUR MOTIVASI ====
        if (motivasiLabel != null) {
            motivasiLabel.setText(generateMotivasi(summary));
        }
        // ==== END FITUR MOTIVASI ====
    }

    // ==== FITUR PENGINGAT ====
    private void startReminder() {
        if (reminderTimeline != null) {
            reminderTimeline.stop();
        }
        // Reminder muncul setiap 60 menit (bisa diganti sesuai kebutuhan)
        reminderTimeline = new Timeline(new KeyFrame(Duration.minutes(60), e -> showReminder()));
        reminderTimeline.setCycleCount(Timeline.INDEFINITE);
        reminderTimeline.play();
    }

    private void showReminder() {
        if (sudahInputHariIni()) return; // Hanya muncul jika BELUM input hari ini
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pengingat HealTrack");
            alert.setHeaderText(null);
            alert.setContentText("Jangan lupa input data kesehatan harianmu ya!");
            alert.showAndWait();
        });
    }

    // Cek apakah user sudah input data hari ini
    private boolean sudahInputHariIni() {
        try (BufferedReader reader = new BufferedReader(new FileReader("health_entries.txt"))) {
            String line;
            String today = LocalDate.now().toString();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5
                        && parts[0].equals(today)
                        && parts[4].equals(currentUser.getUsername())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // ==== END FITUR PENGINGAT ====

    // ==== FITUR MOTIVASI ====
    private String generateMotivasi(Map<String, Double> summary) {
        // Target harian (bisa kamu sesuaikan)
        double targetMakan = 2000;      // kalori
        double targetOlahraga = 30;     // menit
        double targetTidur = 8;         // jam
        double targetAir = 8;           // gelas

        double makan = summary.getOrDefault("Makan", 0.0);
        double olahraga = summary.getOrDefault("Olahraga", 0.0);
        double tidur = summary.getOrDefault("Tidur", 0.0);
        double air = summary.getOrDefault("Air", 0.0);

        // Motivasi sesuai progres
        if (makan < targetMakan * 0.5)
            return "Ayo, jangan lupa sarapan sehat hari ini! 💪🍚";
        if (air < targetAir * 0.7)
            return "Ingat minum air putih yang cukup ya! 🥤";
        if (olahraga < targetOlahraga)
            return "Sedikit olahraga lagi yuk! Biar makin bugar 🔥";
        if (tidur < targetTidur)
            return "Jangan lupa istirahat yang cukup supaya tetap fit! 😴";
        if (makan >= targetMakan && olahraga >= targetOlahraga && tidur >= targetTidur && air >= targetAir)
            return "Keren banget! Semua target harianmu tercapai 🎉👏";

        return "Semangat! Target harianmu tinggal sedikit lagi, kamu pasti bisa! 🚀";
    }
    // ==== END FITUR MOTIVASI ====

    @FXML
    private void handleInputData() {
        if (currentUser != null) {
            Main.showInputView(currentUser);
        }
    }

    @FXML
    private void handleHistory() {
        if (currentUser != null) {
            Main.showHistoryView(currentUser);
        }
    }

    @FXML
    private void handleProfile() {
        if (currentUser != null) {
            Main.showProfileView(currentUser);
        }
    }

    @FXML
    private void handleEditProfile() {
        if (currentUser != null) {
            Main.showEditProfile(currentUser);
        }
    }
}