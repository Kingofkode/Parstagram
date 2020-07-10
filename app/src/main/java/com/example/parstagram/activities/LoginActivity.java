package com.example.parstagram.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parstagram.databinding.ActivityLoginBinding;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
  private static final String TAG = "LoginActivity";

  public static final int SIGN_UP_REQUEST_CODE = 4;

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

  // User pressed the "Sign up" button
  public void onSignUpClick(View view) {
    Intent signUpIntent = new Intent(this, SignUpActivity.class);
    startActivityForResult(signUpIntent, SIGN_UP_REQUEST_CODE);
  }

  private void loginUser(String username, String password) {
    Log.i(TAG, "Attempting to login user...");

    final ProgressDialog dialog = ProgressDialog.show(this, "",
      "Logging in ...", true);

    ParseUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        dialog.dismiss();
        if (e != null) {
          Log.e(TAG, "Issue with login", e);
          Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
          return;
        }
        // Go to the main activity if the user has signed in successfully
        launchMainActivity();
      }
    });
  }

  private void launchMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == SIGN_UP_REQUEST_CODE && resultCode == RESULT_OK) {

      // User signed up for an account!
      // Navigate to main activity
      Intent mainActivityIntent = new Intent(this, MainActivity.class);
      startActivity(mainActivityIntent);
      finish();
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
}