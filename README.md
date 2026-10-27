# Balloon Pop: Android Mini-Game Collection

A native Android arcade application featuring 2D physics simulation, multi-touch collision handling, and multiple mini-game modes. Built with Java and the Android SDK using SurfaceView and custom game threads for 60 FPS rendering.

## Game Modes

- Classic Mode: Balloons ascend at variable speeds and point values. The objective is to pop them before they exit the top boundary.
- Hazard Mode: Bomb obstacles spawn among standard balloons. Touching a bomb results in penalties or game over.
- Time Attack Mode: Fixed 60-second round testing rapid touch accuracy with combo multipliers.
- Sensor Mode: Uses hardware accelerometer data to influence balloon drift and trajectory.

## Technical Architecture

- Rendering Engine: Multi-threaded game loop running on a dedicated worker thread via SurfaceView.
- Collision Detection: Euclidean distance calculation per touch event.
- State Management: Activity lifecycle handling with pause/resume synchronization.
- Persistence: SharedPreferences and SQLite for local high scores.

## Tech Stack

- Language: Java 17+
- Platform: Android SDK (minSdkVersion 24, targetSdkVersion 34)
- Tooling: Android Studio, Gradle

## Project Structure

- app/src/main/java/ - Core game loop, surface views, and entity models
- app/src/main/res/ - XML layouts, strings, and drawables
- app/build.gradle - Build configuration and SDK dependencies

## Setup

1. Clone the repository:
   git clone https://github.com/YUVRAJ0824/ballon_games.git
2. Open the project in Android Studio.
3. Sync Gradle dependencies.
4. Run on an Android device or emulator running API 24+.
