# Balloon Pop: Android-Based Mini-Game Collection

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android)
![Architecture](https://img.shields.io/badge/Architecture-Event--Driven-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

An interactive, multi-threaded mobile arcade application combining 3–4 high-reflex mini-games built natively in Java for Android. Features real-time touch coordinate collision handling, optimized object pooling for zero-lag rendering, dynamic difficulty scaling, and persistent high-score leaderboards.

---

## 🎮 Game Modes & Features

- **🎈 Classic Balloon Pop:** Rapid reflex game with ascending balloons of varying speeds, point values, and sizes.
- **💣 Hazard & Bomb Avoidance:** Dynamic bomb spawns that penalize score or trigger instant game over upon accidental tap.
- **⏱️ Time Rush Challenge:** 60-second frenzy mode testing multi-finger tapping speed with multiplier combos.
- **🎨 Special Balloon Variations:**
  - *Golden Balloon:* High-point reward bonus.
  - *Freeze Balloon:* Temporarily slows down all balloon ascent velocities.
  - *Rainbow Burst:* Pops all adjacent balloons in an explosion radius.
- **🏆 Persistent Leaderboard:** Local SQLite / SharedPreferences storage saving top scores, accuracy, and play streaks.

---

## 🏗️ Technical Architecture & Design Highlights

- **Object Pooling Pattern:** Recycles balloon objects to prevent excessive memory allocation and avoid Android Garbage Collection (GC) frame stutters.
- **Multi-Threaded Game Loop:** Dedicated rendering and physics updates running on a separate thread from the Android Main/UI thread to maintain steady 60 FPS.
- **Event-Driven Touch Dispatching:** Custom view coordinate calculation with multi-touch support for fast consecutive taps.
- **Sound & Haptics:** Low-latency SoundPool audio playback and dynamic vibrational haptic feedback.

---

## 🛠️ Technology Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 17+ |
| **Platform** | Android SDK (API Level 26–34) |
| **IDE** | Android Studio |
| **Audio** | Android SoundPool API (Low-latency audio) |
| **Storage** | SQLite / SharedPreferences |
| **UI & Layouts** | Native Android XML Layouts & Custom Canvas Views |

---

## 🚀 Setup & Installation

1. **Clone the repository:**
   `ash
   git clone https://github.com/YUVRAJ0824/ballon_games.git
   cd ballon_games
   `
2. **Open in Android Studio:**
   - Select **File > Open** and choose the project directory.
   - Allow Gradle to sync dependencies.
3. **Run on Device or Emulator:**
   - Select your target Android device (API 26+) and click **Run (Shift + F10)**.

---

## 📜 Academic Project Details
- **Course:** Master of Computer Applications (MCA) — Final Year
- **Project Title:** Balloon Pop: Android-Based Mini-Game Collection
