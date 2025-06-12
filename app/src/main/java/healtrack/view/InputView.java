package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class InputView {

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField valueField;

    @FXML
    private TextField notesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private User currentUser;

    public void initialize() {
        typeComboBox.getItems().addAll("Makan", "Olahraga", "Tidur", "Air"); // <-- SAMAKAN dengan DashboardView!
        saveButton.setOnAction(e -> saveEntry());
        cancelButton.setOnAction(e -> cancelEntry());
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    private void saveEntry() {
        String type = typeComboBox.getValue();
        String valueText = valueField.getText();
        String notes = notesField.getText();

        if (type == null || valueText.isEmpty()) {
            showAlert("Input Tidak Lengkap", "Silakan pilih jenis data dan masukkan nilai.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double value = Double.parseDouble(valueText);
            LocalDate date = LocalDate.now();

            // Simpan ke file dengan format: tanggal;tipe;nilai;catatan;username
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("health_entries.txt", true))) {
                writer.write(date + ";" + type + ";" + value + ";" + notes + ";" + currentUser.getUsername());
                writer.newLine();
            }

            showAlert("Sukses", "Data berhasil disimpan!", Alert.AlertType.INFORMATION);

            // Kembali ke dashboard dan data akan auto-refresh (karena DashboardView/HistoryView baca file setiap setUser)
            Main.showDashboard(currentUser);

        } catch (NumberFormatException e) {
            showAlert("Format Salah", "Nilai harus berupa angka.", Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Gagal", "Terjadi kesalahan saat menyimpan data.", Alert.AlertType.ERROR);
        }
    }

    private void cancelEntry() {
        try {
            Main.showDashboard(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}