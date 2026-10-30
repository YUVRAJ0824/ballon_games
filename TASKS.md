# 🎈 BALLOON POP: ANDROID MINI-GAME COLLECTION — DAILY 3% TASK TRACKER

> **Project:** Final Year MCA Project  
> **App:** Balloon Pop — 4 Mini-Game Collection  
> **Platform:** Android (Java, Android Studio)  
> **Repo:** https://github.com/YUVRAJ0824/ballon_games.git  
> **Started:** 2026-10-30  

---

## 🎮 MINI-GAMES OVERVIEW

| # | Mini-Game | What Player Does | MCA Technical Concepts Demonstrated |
|---|-----------|-----------------|--------------------------------------|
| 1 | **Classic Pop** | Tap rising balloons before they escape the top | SurfaceView rendering, Game Loop, Touch Events, Object Pooling, Collision Detection |
| 2 | **Bomb Dodge** | Pop balloons but avoid tapping bombs | Polymorphism (BalloonType), Game State Machines, Penalty Logic, Difficulty Curves |
| 3 | **Time Attack** | Pop max balloons in 60 seconds, chain combos | Timer Threads, Combo Multiplier Algorithm, Concurrent Data Structures |
| 4 | **Tilt & Pop** | Tilt phone to move balloons, then tap to pop | Hardware Sensor API (Accelerometer), Physics Simulation, Real-time Sensor Fusion |

---

## ✅ COMPLETED
- [x] Initial GitHub repository connection
- [x] Project architecture and technical specification README
- [x] Master plan created

---

## 🔨 MINI-GAME 1: CLASSIC POP

### Phase 1 — Foundation (Days 1–5 → 3% to 15%)

- [x] **Day 1 (3%)** ✅ — Project Rebranding & Balloon Model *(2026-10-30)*
  - ✅ Renamed package `com.stevekb.bounce` → `com.yuvraj.balloonpop`
  - ✅ Updated `applicationId`, app name, AndroidManifest.xml
  - ✅ Modernized `build.gradle` (compileSdk 34, minSdk 24, targetSdk 34)
  - ✅ Removed old AdMob dependency
  - ✅ Created `Balloon.java` model class (x, y, speed, radius, color, type, alive)
  - ✅ Created `BalloonType.java` enum (NORMAL, BOMB, BONUS)
  - ✅ Created MainActivity (Main Menu with 4 game buttons)
  - ✅ Created GameActivity (hosts GameSurface + HUD)
  - ✅ Created GameSurface.java skeleton (SurfaceView + sky background + balloon drawing)
  - ✅ Created GameThread.java (60 FPS game loop)
  - ✅ Created all XML layouts (menu, game, HUD)
  - ✅ Created colors.xml, styles.xml, updated strings.xml

- [ ] **Day 2 (6%)** — Object Pool & Spawn System
  - Create `BalloonPool.java` (pre-allocate & recycle balloon objects)
  - Balloon spawn logic (random x-position, random size, random speed)
  - Spawn rate timer (new balloon every N milliseconds)

- [ ] **Day 3 (9%)** — Game Surface & 60 FPS Game Loop
  - Create new `GameSurface.java` extending SurfaceView
  - Implement proper 60 FPS game loop in `GameThread.java`
  - Delta-time based updates (frame-rate independent movement)
  - Surface lifecycle (create/destroy thread safely)

- [ ] **Day 4 (12%)** — Balloon Drawing & Visuals
  - Draw balloons as colored ovals with gradient shading
  - Draw balloon string (line below the oval)
  - Random vibrant colors for each balloon
  - Size variation (small = fast, big = slow)

- [ ] **Day 5 (15%)** — Balloon Rising Animation
  - Balloons spawn below screen bottom
  - Rise upward at varying speeds
  - Gentle horizontal sway (sine wave drift)
  - Remove balloon when it exits top of screen

### Phase 2 — Core Gameplay (Days 6–12 → 18% to 36%)

- [ ] **Day 6 (18%)** — Touch-to-Pop Detection
  - Euclidean distance collision: tap point vs balloon center
  - Handle multi-touch (pop multiple balloons at once)
  - Visual feedback on tap (brief flash)

- [ ] **Day 7 (21%)** — Pop Animation Effect
  - Burst particle effect on pop (small circles fly outward)
  - Scale-down + fade-out animation
  - Satisfying visual feedback

- [ ] **Day 8 (24%)** — Score System
  - Points per pop (small balloon = more points, big = fewer)
  - Running score counter
  - Score popup text at pop location (+10, +25, etc.)

- [ ] **Day 9 (27%)** — HUD Overlay (ScoreHUD.java)
  - Score display (top-left)
  - Lives display (top-right, heart icons)
  - Current level/wave indicator (top-center)
  - Semi-transparent HUD background

- [ ] **Day 10 (30%)** — Lives & Game Over
  - Start with 3 lives
  - Lose 1 life when a balloon escapes the top
  - Screen flash red on life lost
  - Game Over state when lives = 0

- [ ] **Day 11 (33%)** — Difficulty Progression
  - Speed increases every 500 points
  - More balloons spawn simultaneously at higher levels
  - Smaller balloons appear more frequently
  - Max difficulty cap

- [ ] **Day 12 (36%)** — Wave/Level System
  - Levels 1–10 with increasing difficulty
  - Brief "Level X" announcement between waves
  - Short pause between levels
  - Level-complete celebration effect

### Phase 3 — UI Screens (Days 13–18 → 39% to 54%)

- [ ] **Day 13 (39%)** — Main Menu Activity & Layout
  - `activity_main_menu.xml` layout
  - App title with balloon animation
  - 4 game mode buttons (Game 1 active, others locked)
  - High Score display on menu
  - Settings button

- [ ] **Day 14 (42%)** — Game Over Screen
  - `GameOverDialog.java`
  - Final score display with animation
  - High score check ("NEW HIGH SCORE!" banner)
  - "Play Again" and "Main Menu" buttons
  - Share score button (Intent)

- [ ] **Day 15 (45%)** — High Score Persistence
  - `ScoreManager.java` using SharedPreferences
  - Save high score per game mode
  - Load and display on menu and game-over
  - Reset scores option in settings

- [ ] **Day 16 (48%)** — Sound Effects
  - `SoundManager.java` using SoundPool
  - Pop sound effect (balloon burst)
  - Bomb explosion sound
  - Level-up jingle
  - Game over sound
  - Background music toggle

- [ ] **Day 17 (51%)** — Background & Visual Polish
  - Sky gradient background (light blue → white)
  - Animated clouds drifting across
  - Grass/ground at bottom
  - Parallax scrolling effect

- [ ] **Day 18 (54%)** — Pause/Resume & Lifecycle
  - Pause button on game screen
  - Pause overlay (dim screen + "PAUSED" text)
  - Proper Activity lifecycle (onPause/onResume saves state)
  - Back button = pause (not exit)

### Phase 4 — Testing & Polish (Days 19–22 → 57% to 66%)

- [ ] **Day 19 (57%)** — Bug Fixes & Crash Handling
  - Test on multiple screen sizes
  - Fix any thread synchronization issues
  - Handle edge cases (rapid tapping, rotation)
  - Null safety checks

- [ ] **Day 20 (60%)** — Performance Optimization
  - Profile frame rate (maintain 60 FPS)
  - Optimize balloon pool recycling
  - Reduce garbage collection pauses
  - Memory leak check

- [ ] **Day 21 (63%)** — Difficulty Balancing
  - Playtest and tune spawn rates
  - Adjust points per balloon type
  - Balance lives vs difficulty curve
  - Make it fun but challenging

- [ ] **Day 22 (66%)** — Game 1 Complete ✅
  - Full end-to-end playtest
  - All features working together
  - Screenshots for documentation
  - **MILESTONE: Mini-Game 1 "Classic Pop" DONE**

---

## 🔨 MINI-GAME 2: BOMB DODGE

### Phase 5 — Bomb Dodge (Days 23–28 → 69% to 84%)

- [ ] **Day 23 (69%)** — Game Mode Architecture
  - Create `BaseGame.java` abstract class
  - Refactor Classic Pop to extend BaseGame
  - Create `BombDodgeGame.java` extending BaseGame
  - Game mode selection from main menu

- [ ] **Day 24 (72%)** — Bomb Balloon Entity
  - Bomb visual (dark balloon with skull/X icon)
  - Bomb spawns mixed with normal balloons
  - Bomb ratio increases with score (10% → 30%)

- [ ] **Day 25 (75%)** — Bomb Mechanics
  - Tap bomb = instant game over (or lose 2 lives)
  - Screen shake effect on bomb tap
  - Explosion animation for bombs
  - Close-call detection (near-miss bonus points)

- [ ] **Day 26 (78%)** — Bonus Balloons
  - Golden/star balloon = 5x points
  - Rare spawn (5% chance)
  - Glowing animation to attract attention
  - Special pop effect

- [ ] **Day 27 (81%)** — Bomb Dodge Polish
  - Difficulty curve tuning
  - Score multiplier for streaks without hitting bombs
  - Warning indicator when bomb is about to appear

- [ ] **Day 28 (84%)** — Game 2 Complete ✅
  - Full playtest
  - Unlock Game 2 button on menu
  - **MILESTONE: Mini-Game 2 "Bomb Dodge" DONE**

---

## 🔨 MINI-GAME 3: TIME ATTACK

### Phase 6 — Time Attack (Days 29–31 → 87% to 93%)

- [ ] **Day 29 (87%)** — 60-Second Timer Mode
  - `TimeAttackGame.java` extending BaseGame
  - Countdown timer (60 → 0)
  - Balloons spawn much faster
  - No lives system (just time)

- [ ] **Day 30 (90%)** — Combo System
  - Combo counter (pops within 0.5 seconds of each other)
  - Multiplier: x2, x3, x4, x5 max
  - Combo text animation on screen
  - Combo break notification

- [ ] **Day 31 (93%)** — Game 3 Complete ✅
  - Time Attack polish & balance
  - Frenzy mode in last 10 seconds (balloons go crazy)
  - **MILESTONE: Mini-Game 3 "Time Attack" DONE**

---

## 🔨 MINI-GAME 4: TILT & POP

### Phase 7 — Tilt & Pop + Final (Days 32–34 → 96% to 100%)

- [ ] **Day 32 (96%)** — Accelerometer Balloon Control
  - `TiltPopGame.java` extending BaseGame
  - Balloons drift based on device tilt
  - Physics simulation (gravity direction changes)
  - Balloons bounce off screen edges

- [ ] **Day 33 (99%)** — Tilt & Pop Polish + SQLite Leaderboard
  - `DatabaseHelper.java` — SQLite leaderboard
  - Top 10 scores per game mode
  - Player name entry on high score
  - Leaderboard screen

- [ ] **Day 34 (100%)** — FINAL RELEASE 🎉
  - All 4 games fully functional
  - Final UI polish & app icon
  - APK build for submission
  - **PROJECT COMPLETE**

---

## 📝 POST-COMPLETION (Bonus / College Submission)

- [ ] Task: College Project Report (30–40 pages)
  - Abstract, Introduction, Literature Survey
  - System Requirements, Design (UML diagrams)
  - Implementation, Screenshots
  - Testing, Conclusion, References

- [ ] Task: Presentation PPT Deck (15–20 slides)

---

## 📊 PROGRESS TRACKER

| Game | Status | Progress |
|------|--------|----------|
| Classic Pop | 🟡 In Progress | 3% (Day 1 done) |
| Bomb Dodge | 🔴 Not Started | 0% |
| Time Attack | 🔴 Not Started | 0% |
| Tilt & Pop | 🔴 Not Started | 0% |
| **Overall** | **🟡 In Progress** | **3%** |
