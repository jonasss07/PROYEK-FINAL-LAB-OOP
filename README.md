<h1 align="center">🩺 HealTrack 🩺</h1>
<p align="center"><strong>A Personal Health Trackker App</strong></p>

## 📝 Deskripsi Singkat

**HealTrack** adalah aplikasi desktop berbasis **Java Fx** yang memudahkan pengguna melacak aktivitas kesehatannya setiap hari. Pengguna dapat mencatat:
- Asupan makanan  
- Olahraga  
- Tidur  
- Minum air  

Aplikasi ini menyajikan **ringkasan harian dan mingguan**, serta mendukung sistem **login dan profil pengguna**.

---

## 🎯 Tujuan Pengembangan

- Menyediakan alat praktis untuk memantau kebiasaan sehat secara mandiri  
- Meningkatkan kesadaran kesehatan melalui pencatatan harian  
- Menyajikan aplikasi Java Swing sederhana namun fungsional  

---

## 🔧 Fitur Utama

1. Login & Register Pengguna  
2. Input Data Kesehatan (makanan, olahraga, tidur, air)  
3. Dashboard Ringkasan Harian  
4. Riwayat Mingguan  
5. Profil Pengguna (tinggi, berat, target)  
6. Pengingat Pop-up Sederhana (opsional)  

---

## 🧩 Struktur Kelas (Ringkas)

**model/**
- `User`: Menyimpan data pengguna dan menghitung BMI  
- `HealthEntry` (abstract class): Superclass entri dengan atribut tanggal, tipe, nilai, dan catatan  
- `Tracker`: Menyimpan seluruh entri dan menyediakan metode seperti `addEntry()`, `getSummaryByDate()`, dll  

**controller/**
- `AppController`: Menangani login, register, input, dan menjadi penghubung model ↔ GUI  

**view/**
- `LoginFrame`: UI untuk login dan register  
- `DashboardFrame`: Menampilkan ringkasan harian dan tombol akses  
- `InputFrame`: Form input data kesehatan  
- `HistoryFrame` (opsional): Tabel riwayat mingguan  

---

## 💡 Penerapan OOP

| Pilar OOP     | Implementasi                                                                 |
|---------------|-------------------------------------------------------------------------------|
| Encapsulation | Atribut pada `User`, `HealthEntry` bersifat private dengan getter/setter     |
| Inheritance   | `HealthEntry` sebagai superclass abstrak                                     |
| Abstraction   | `HealthEntry` menyembunyikan detail spesifik dari entri                      |
| Polymorphism  | `getSummary()` dapat diimplementasikan berbeda di subclass                   |

---

## 🔄 Flow Aplikasi

1. Pengguna membuka aplikasi → Login/Register  
2. Masuk ke dashboard → Lihat ringkasan harian  
3. Input data aktivitas harian  
4. Lihat riwayat mingguan  
5. Kelola data profil  

---

## 📁 Struktur Folder Proyek HealTrack
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

---
## Kontributor
- Nama: Ishmah Nurwasilah
- Nama: Jonas Ba'ka
