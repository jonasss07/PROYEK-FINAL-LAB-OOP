<h1 align="center">🩺 HealTrack 🩺</h1>
<p align="center"><strong>A Personal Health Tracker App</strong></p>
<div align="center">
  <img src="Healtrack_logo.png" alt="HealTrack Logo" height="200"/>
</div>



---

## 🌐 Konsep Umum

**HealTrack** adalah aplikasi desktop berbasis **JavaFX (Gradle)** yang dirancang untuk membantu pengguna dalam melacak dan memantau aktivitas kesehatan mereka secara konsisten setiap hari. Dengan antarmuka yang intuitif dan fitur yang lengkap, HealTrack bertujuan menjadi asisten kesehatan pribadi digital yang sederhana namun bermanfaat.

---

## 🧩 Fitur Utama

### 🍽️ Input Aktivitas Harian

Catat konsumsi makanan, olahraga, waktu tidur, dan jumlah air minum dengan mudah.

### 📊 Dashboard Ringkasan

Tampilkan ringkasan data harian dan mingguan dalam tampilan yang rapi.

### 👤 Profil Kesehatan

Kelola data tinggi, berat, dan target kesehatan pengguna. Termasuk fitur kalkulasi BMI.

### 🔐 Sistem Login & Register

Autentikasi pengguna untuk menyimpan data personal secara terpisah.

### ⏰ Pengingat Kesehatan (Opsional)

Pop-up friendly sebagai pengingat aktivitas sehat.

---

## 📦 Struktur Proyek

### 🔹 Kelas Utama

* **Main.java**  
  Titik masuk aplikasi. Menyiapkan stage awal dan memuat tampilan login.

* **AppController.java**  
  Mengatur alur kerja aplikasi, mulai dari login, registrasi, hingga input data dan navigasi antar tampilan.

---

### 🧍‍♂️ Model Pengguna & Data Kesehatan

* **User.java**  
  Mewakili pengguna aplikasi dengan atribut dasar (nama, tinggi, berat, target).  
  Termasuk metode `calculateBMI()`.

* **HealthEntry.java** *(abstract)*  
  Superclass untuk semua jenis entri (makanan, olahraga, tidur, air), dengan atribut:
  - `tanggal`, `tipe`, `nilai`, `catatan`

* **Tracker.java**  
  Menyimpan daftar `HealthEntry` dan menyediakan metode:  
  - `addEntry()`, `getEntriesByDate()`, `getWeeklySummary()`

---

### 🖼️ View (FXML + JavaFX UI)

* **LoginView.java** & `login_view.fxml`  
  Tampilan login dan registrasi pengguna.

* **DashboardView.java** & `dashboard_view.fxml`  
  Ringkasan aktivitas hari ini, shortcut ke fitur utama.

* **InputView.java** & `input_view.fxml`  
  Form input data (makanan, olahraga, tidur, air).

* **HistoryView.java** & `history_view.fxml`  
  Riwayat aktivitas mingguan dalam bentuk tabel.

* **ProfileView.java** & `profile_view.fxml`  
  Tampilkan dan edit informasi profil pengguna.

---

## 💡 Konsep OOP yang Diimplementasikan

| Konsep        | Implementasi                                                              |
| ------------- | ------------------------------------------------------------------------- |
| Inheritance   | `HealthEntry` menjadi superclass dari berbagai entri kesehatan.           |
| Abstraction   | Detail dari entri disembunyikan di dalam `HealthEntry`.                   |
| Encapsulation | Atribut pada `User`, `HealthEntry` bersifat private dengan getter/setter. |
| Polymorphism  | `getSummary()` dapat dioverride oleh subclass entri spesifik.             |

---

## ▶️ Cara Menjalankan (JavaFX Gradle)

1. Pastikan Java dan JavaFX SDK telah diinstal.
2. Jalankan aplikasi via Gradle:
```bash
./gradlew run
```

## 📁 Struktur Folder HealTrack

```bash
src/
└── main/
    ├── java/
    │   └── healtrack/
    │       ├── model/
    │       │   ├── HealthEntry.java
    │       │   ├── Tracker.java
    │       │   └── User.java
    │       ├── view/
    │       │   ├── DashboardView.java
    │       │   ├── EditProfileView.java
    │       │   ├── HistoryView.java
    │       │   ├── InputView.java
    │       │   ├── LoginView.java
    │       │   └── ProfileView.java
    │       └── controller/
    │           └── AppController.java
    │       └── Main.java
    │
    ├── resources/
    │   └── healtrack/
    │       ├── style/
    │       │   ├── default_avatar.png
    │       │   └── style.css
    │       └── view/
    │           ├── dashboard_view.fxml
    │           ├── edit_profile.fxml
    │           ├── history_view.fxml
    │           ├── input_view.fxml
    │           ├── login_view.fxml
    │           └── profile_view.fxml
```
## Author
<a href="https://github.com/jonasss07/PROYEK-FINAL-LAB-OOP/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=jonasss07/PROYEK-FINAL-LAB-OOP" />
</a>
