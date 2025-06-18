# Battleship Game 🚢

A Java Swing application for a classic two-player Battleship game using JNI for core logic and JUnit for testing.

## 🔧 Features

- Two-player turn-based GUI game
- Native game logic with JNI (`System.loadLibrary("BattleShip")`)
- JUnit test suite
- Win detection, turn management, reset function

## ▶️ How to Run

1. Compile:

```bash
javac -d out src/Main.java

Run (with native lib in path):
java -cp out -Djava.library.path=./native Main

Run tests (JUnit 5 required):
javac -cp .:junit-platform-console-standalone.jar test/MainTest.java
java -jar junit-platform-console-standalone.jar -cp . --scan-classpath

Developed by Ceren with Java, JNI, and JUnit 💥