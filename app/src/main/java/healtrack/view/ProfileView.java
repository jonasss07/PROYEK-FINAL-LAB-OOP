package healtrack.view;

import healtrack.Main;
import healtrack.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;

public class ProfileView {
    @FXML private ImageView avatarImage;
    @FXML private Label nameLabel;
    @FXML private Label heightLabel;
    @FXML private Label weightLabel;
    @FXML private Label bmiLabel;
    @FXML private Button pilihGambarButton;

    private User user;

    @FXML
    public void initialize() {
        avatarImage.setPreserveRatio(true);
        avatarImage.setFitWidth(120);
        avatarImage.setFitHeight(120);

        // Terapkan masking bulat setelah layout siap
        avatarImage.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            applyCircleMask();
        });

        // Aksi tombol pilih gambar
        if (pilihGambarButton != null) {
            pilihGambarButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Pilih Foto Profil");
                fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
                );

                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    String path = selectedFile.getAbsolutePath();
                    user.setAvatarPath(path); // simpan ke user
                    avatarImage.setImage(new Image("file:" + path));
                    applyCircleMask();
                    try {
                        user.saveToFile();
                    } catch (Exception ex) {
                        System.err.println("Gagal menyimpan user: " + ex.getMessage());
                    }
                }
            });
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (nameLabel != null) nameLabel.setText(user.getName());
        if (heightLabel != null) heightLabel.setText(user.getHeight() + " cm");
        if (weightLabel != null) weightLabel.setText(user.getWeight() + " kg");
        if (bmiLabel != null) bmiLabel.setText(String.format("%.2f", user.calculateBMI()));

        // Load avatar image
        if (avatarImage != null) {
            avatarImage.setPreserveRatio(true);
            avatarImage.setFitWidth(120);
            avatarImage.setFitHeight(120);

            if (user.getAvatarPath() != null && !user.getAvatarPath().isEmpty()) {
                File f = new File(user.getAvatarPath());
                if (f.exists()) {
                    avatarImage.setImage(new Image("file:" + f.getAbsolutePath()));
                } else {
                    java.net.URL url = getClass().getResource("/healtrack/style/default_avatar.png");
                    if (url != null) {
                        avatarImage.setImage(new Image(url.toExternalForm()));
                    } else {
                        System.err.println("default_avatar.png NOT FOUND in resources!");
                        avatarImage.setImage(null);
                    }
                }
            } else {
                java.net.URL url = getClass().getResource("/healtrack/style/default_avatar.png");
                if (url != null) {
                    avatarImage.setImage(new Image(url.toExternalForm()));
                } else {
                    System.err.println("default_avatar.png NOT FOUND in resources!");
                    avatarImage.setImage(null);
                }
            }
            applyCircleMask();
        }
    }

    private void applyCircleMask() {
        double width = avatarImage.getFitWidth();
        double height = avatarImage.getFitHeight();
        double radius = Math.min(width, height) / 2;
        Circle clip = new Circle(width / 2, height / 2, radius);
        avatarImage.setClip(clip);
    }

    @FXML
    private void openEditProfile() {
        Main.showEditProfile(user);
    }
}