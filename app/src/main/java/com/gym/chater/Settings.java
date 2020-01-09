package com.gym.chater;

public class Settings {
    private static boolean soundEffects;

    public static boolean isSoundEffects() {
        return soundEffects;
    }

    public static void setSoundEffects(boolean soundEffects) {
        Settings.soundEffects = soundEffects;
    }
}