package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.HashMap;
import java.util.Map;

public class LoginView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private final Map<String, User> userDatabase = new HashMap<>();

    public void initialize() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Gagal", "Username dan password wajib diisi.", AlertType.ERROR);
            return;
        }

        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            showAlert("Login Berhasil", "Selamat datang, " + username + "!", AlertType.INFORMATION);
            try {
                Main.showDashboard(user);
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error", "Gagal membuka dashboard.", AlertType.ERROR);
            }
        } else {
            showAlert("Login Gagal", "Username atau password salah.", AlertType.ERROR);
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Registrasi Gagal", "Username dan password wajib diisi.", AlertType.ERROR);
            return;
        }

        if (userDatabase.containsKey(username)) {
            showAlert("Registrasi Gagal", "Username sudah terdaftar.", AlertType.WARNING);
        } else {
            User newUser = new User(username, password);
            userDatabase.put(username, newUser);
            showAlert("Registrasi Berhasil", "Pengguna " + username + " berhasil dibuat!", AlertType.INFORMATION);
        }
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}