package com.example.androiddiplom.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddiplom.App;
import com.example.androiddiplom.R;
import com.example.androiddiplom.key.Keystore;

public class LoginActivity extends AppCompatActivity {
    private com.example.androiddiplom.key.Keystore Keystore;
    private EditText pin;
    private int progress = 0;
    private EditText edTxt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Keystore = App.getComponent().getKeystore();
        progressBar = findViewById(R.id.bar_progress);
        edTxt = findViewById(R.id.edTxt_pinCode);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        edTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (before) {
                    case 0:
                        progress += 1;
                        break;
                    case 1:
                        progress -= 1;
                        break;
                }
                progressBar.setProgress(progress);
                if (isProgressDone()) {
                    Keystore = edTxt.getText().toString();
                    if (isPinSaved()) {
                        checkPin();
                    } else {
                        savePin();
                        toLogin();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    private void toLogin() {
        Intent toLogin = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(toLogin);
        finish();
    }

    private boolean isProgressDone() {
        return (progress == 4);
    }

    private boolean isPinSaved() {
        return (Keystore.contains(PREF_PIN));
    }

    private void checkPin() {
        if (pin.equals(Keystore.getString(PREF_PIN, ""))) {
            toLogin();
        } else {
            edTxt.setText("");
            progress = 0;
            progressBar.setProgress(progress);
            Toast.makeText(this, R.string.toast_wrong_pin, Toast.LENGTH_SHORT).show();
        }
    }

    private void savePin() {
        Keystore.edit()
                .putString(PREF_PIN, pin)
                .apply();

    }
}