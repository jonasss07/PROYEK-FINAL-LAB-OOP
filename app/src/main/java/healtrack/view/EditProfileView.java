package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditProfileView {

    @FXML
    private TextField nameField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField targetBmiField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        nameField.setText(user.getUsername());
        heightField.setText(String.valueOf(user.getHeight()));
        weightField.setText(String.valueOf(user.getWeight()));
        targetBmiField.setText(String.valueOf(user.getTargetBmi()));
    }

    @FXML
    private void saveProfile() {
        try {
            String name = nameField.getText();
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double targetBmi = Double.parseDouble(targetBmiField.getText());

            currentUser.setUsername(name);
            currentUser.setHeight(height);
            currentUser.setWeight(weight);
            currentUser.setTargetBmi(targetBmi);
            currentUser.saveToFile();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText(null);
            alert.setContentText("Profil berhasil diperbarui!");
            alert.showAndWait();

            Main.showDashboard(currentUser);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Pastikan semua nilai numerik diisi dengan benar.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelEdit() {
        try {
            currentUser.saveToFile();
            Main.showDashboard(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
