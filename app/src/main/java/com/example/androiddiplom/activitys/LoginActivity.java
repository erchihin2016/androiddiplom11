package com.example.androiddiplom.activitys;

import android.content.Context;
import android.content.Intent;
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
    private Keystore keystore = App.getComponent().getKeystore();
    private int progress = 0;
    private EditText edTxt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        return (keystore.hasPin());
    }

    private void checkPin() {
        if (keystore.checkPin(edTxt.getText().toString())) {
            toLogin();
        } else {
            edTxt.setText("");
            progress = 0;
            progressBar.setProgress(progress);
            Toast.makeText(this, R.string.toast_wrong_pin, Toast.LENGTH_SHORT).show();
        }
    }

    private void savePin() {
        keystore.saveNew(edTxt.getText().toString());
    }
}