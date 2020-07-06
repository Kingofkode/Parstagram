package com.example.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parstagram.databinding.ActivityLoginBinding;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Done with view binding

        // User has already logged in
        if (ParseUser.getCurrentUser() != null) {
            launchMainActivity();
        }
    }

    // User pressed the "Log In" button
    public void onLoginClick(View view) {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();
        loginUser(username, password);
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user...");
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Go to the main activity if the user has signed in successfully
                launchMainActivity();
                Toast.makeText(LoginActivity.this, "Logged in as " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}