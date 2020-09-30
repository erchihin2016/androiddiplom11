package com.example.androiddiplom.key;

import android.content.SharedPreferences;

public class SimpleKeystore implements Keystore {

    private static final String PREF_PIN = "pref_pin";
    private SharedPreferences preferences;

    public SimpleKeystore(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean hasPin() {
        return preferences.contains(PREF_PIN);
    }

    @Override
    public boolean checkPin(String pin) {
        return pin.equals(preferences.getString(PREF_PIN, null));
    }

    @Override
    public void saveNew(String pin) {
        preferences.edit()
                .putString(PREF_PIN, pin)
                .apply();
    }
}
