package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HistoryView {

    @FXML
    private TableView<HistoryEntry> historyTable;

    @FXML
    private TableColumn<HistoryEntry, String> dateColumn;

    @FXML
    private TableColumn<HistoryEntry, String> typeColumn;

    @FXML
    private TableColumn<HistoryEntry, String> valueColumn;

    @FXML
    private TableColumn<HistoryEntry, String> notesColumn;

    @FXML
    private Button backButton;

    @FXML
    private LineChart<Number, Number> historyChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        loadHistory();
    }

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        // Jangan panggil loadHistory() di sini, panggil di setUser!

        backButton.setOnAction(e -> {
            try {
                if (currentUser != null) {
                    Main.showDashboard(currentUser);
                } else {
                    System.err.println("[HistoryView] currentUser is null saat kembali ke dashboard!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void loadHistory() {
        ObservableList<HistoryEntry> entries = FXCollections.observableArrayList();
        if (currentUser == null) {
            System.err.println("[HistoryView] loadHistory dipanggil tapi currentUser null!");
            historyTable.setItems(entries);
            if (historyChart != null) historyChart.getData().clear();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("health_entries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                // Cek parts[4] = username
                if (parts.length >= 5 && parts[4].equals(currentUser.getUsername())) {
                    entries.add(new HistoryEntry(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        historyTable.setItems(entries);
        updateGraph(entries);
    }

    private void updateGraph(ObservableList<HistoryEntry> entries) {
        if (historyChart == null) return;
        historyChart.getData().clear();

        XYChart.Series<Number, Number> beratSeries = new XYChart.Series<>();
        beratSeries.setName("Grafik Berat Badan");

        int point = 1;
        for (HistoryEntry entry : entries) {
            if (entry.getType().equalsIgnoreCase("berat") || entry.getType().equalsIgnoreCase("weight")) {
                try {
                    double value = Double.parseDouble(entry.getValue());
                    beratSeries.getData().add(new XYChart.Data<>(point, value));
                    point++;
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }
        if (!beratSeries.getData().isEmpty()) {
            historyChart.getData().add(beratSeries);
        }
    }

    public static class HistoryEntry {
        private final String date, type, value, notes;

        public HistoryEntry(String date, String type, String value, String notes) {
            this.date = date;
            this.type = type;
            this.value = value;
            this.notes = notes;
        }

        public String getDate() { return date; }
        public String getType() { return type; }
        public String getValue() { return value; }
        public String getNotes() { return notes; }
    }
}