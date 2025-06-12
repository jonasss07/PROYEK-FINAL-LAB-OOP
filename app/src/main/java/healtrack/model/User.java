package healtrack.model;

import java.io.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private double height;
    private double weight;
    private double targetBMI;
    private String avatarPath; // Path ke foto profil

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Tinggi
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    // Berat
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Target BMI
    public double getTargetBmi() {
        return targetBMI;
    }
    public void setTargetBmi(double targetBMI) {
        this.targetBMI = targetBMI;
    }

    // Avatar (foto profil)
    public String getAvatarPath() {
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    // Alias nama user
    public String getName() {
        return username;
    }

    // Hitung BMI aktual
    public double calculateBMI() {
        if (height <= 0) return 0;
        return weight / Math.pow((height / 100.0), 2);
    }

    // Simpan user ke file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_data.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load user dari file
    public static User loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_data.ser"))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
