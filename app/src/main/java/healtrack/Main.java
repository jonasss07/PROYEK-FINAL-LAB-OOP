package healtrack;

import healtrack.model.User;
import healtrack.view.DashboardView;
import healtrack.view.EditProfileView;
import healtrack.view.InputView;
import healtrack.view.ProfileView;
import healtrack.view.HistoryView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        try {
            showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/login_view.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Login");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            try {
                primaryStage.getIcons().add(new Image("/healtrack/style/logo.png"));
            } catch (Exception ignored) {}
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/dashboard_view.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Dashboard");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();

            DashboardView controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showProfileView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/profile_view.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Profil");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            ProfileView controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showEditProfile(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/edit_profile.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Edit Profil");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            EditProfileView controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showInputView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/input_view.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Input Data");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            InputView controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showHistoryView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/healtrack/view/history_view.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("/healtrack/style/style.css").toExternalForm());
            primaryStage.setTitle("HealTrack - Riwayat");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();

            // PENTING: Pastikan controller.setUser(user) DIPANGGIL agar currentUser tidak null!
            HistoryView controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}