package com.example.ucdlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ProgressBar progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Toast.makeText(getBaseContext(), "Operation canceled", Toast.LENGTH_LONG).show();
    }

    public void loginHandler(View v) {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        Button b = (Button) findViewById(R.id.login);
        b.setEnabled(false);

        final ProgressBar progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setVisibility(View.VISIBLE);

        EditText username_c = (EditText)findViewById(R.id.username);
        EditText password_c = (EditText)findViewById(R.id.password);

        String username = username_c.getText().toString();
        String password = password_c.getText().toString();

        // TODO: check firebase

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progress.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
    }


    public boolean validate() {
        boolean valid = true;

        EditText username_c = (EditText)findViewById(R.id.username);
        EditText password_c = (EditText)findViewById(R.id.password);

        String username = username_c.getText().toString();
        String password = password_c.getText().toString();

        System.out.println("Username: |" + username + "|");
        System.out.println("Password: |" + password + "|");

        if (username.length() == 0)
            valid = false;

        if (password.length() == 0)
            valid = false;

        return valid;
    }

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "Logged in", Toast.LENGTH_LONG).show();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
    }
}
