package model;

public class User {
    private String username;
    private String password;
    private String fullName;
    private double height;
    private double weight;
    private double targetWeight;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public double calculateBMI() {
        if (height <= 0) return 0;
        double heightInMeter = height / 100.0;
        return weight / (heightInMeter * heightInMeter);
    }

    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    public String getUsername() { 
        return username; 
    }

    public String getPassword() { 
        return password; 
    }

    public String getFullName() {
        return fullName;
    }

    public void setHeight(double height) { 
        this.height = height; 
    }

    public void setWeight(double weight) { 
        this.weight = weight; 
    }

    public void setTargetWeight(double targetWeight) { 
        this.targetWeight = targetWeight; 
    }

    public double getHeight() { 
        return height; 
    }

    public double getWeight() { 
        return weight; 
    }

    public double getTargetWeight() { 
        return targetWeight; 
    }
}
