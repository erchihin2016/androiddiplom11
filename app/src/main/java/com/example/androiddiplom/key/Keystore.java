package com.example.androiddiplom.key;

public interface Keystore {

    boolean hasPin();

    boolean checkPin(String pin);

    void saveNew(String pin);
}